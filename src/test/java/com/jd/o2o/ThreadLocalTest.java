package com.jd.o2o;

import java.lang.reflect.Field;
import java.util.concurrent.Callable;

import junit.framework.TestCase;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * 测试不同线程的ThreadLocal的get和set方法：因为线程销毁，ThreadLocal也会被销毁<br />
 * 模拟线程池测试同一线程的ThreadLocal的get和set方法：因为线程一直存活，ThreadLocal一直没有被销毁
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ThreadLocalTest extends TestCase {

  public void test_diffthreadlocal() throws Exception {
    System.out.println("test_diffthreadlocal begin");
    Thread thread = new Thread() {
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
      diffLocal.set(value);
    }
  }

  public void test_samethreadlocal() throws InterruptedException {
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
      System.out.println(local.get());
      local.set("ThreadLocalRunnable");
      System.out.println(local.get());
      System.out.println("call end");
      return null;
    }
  }
}
