package com.github.xionghuicoder.testcase;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ThreadLocalTest extends TestCase {

  @Test
  public void testDiffThreadlocal() throws Exception {
    System.out.println("test_diffthreadlocal begin");
    Thread thread = new Thread() {
      @Override
      public void run() {
        DiffThreadLocal diffLocal = new DiffThreadLocal();
        diffLocal.set("test");
      }
    };
    thread.start();
    thread.join();
    Field field = Thread.class.getDeclaredField("threadLocals");
    field.setAccessible(true);
    Object map = field.get(thread);
    System.out.println(map);
    map = field.get(Thread.currentThread());
    System.out.println(map);
    System.out.println("test_diffthreadlocal end");
  }

  private static class DiffThreadLocal {
    private ThreadLocal<String> diffLocal = new ThreadLocal<String>();

    private void set(String value) {
      this.diffLocal.set(value);
    }
  }

  @Test
  public void testSameThreadlocal() throws InterruptedException {
    System.out.println("test_samethreadlocal begin");
    ThreadLocalRunnable run = new ThreadLocalRunnable();
    Thread thread = new Thread(run);
    thread.start();
    thread.join();
    System.out.println("test_samethreadlocal end");
  }

  private static class ThreadLocalRunnable implements Runnable {

    @Override
    public void run() {
      Callable<String> c = new ThreadLocalCallable();
      try {
        c.call();
        c.call();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static class ThreadLocalCallable implements Callable<String> {
    private ThreadLocal<String> local = new ThreadLocal<String>();

    @Override
    public String call() throws Exception {
      System.out.println("call begin:");
      System.out.println(this.local.get());
      this.local.set("ThreadLocalRunnable");
      System.out.println(this.local.get());
      System.out.println("call end");
      return null;
    }
  }
}
