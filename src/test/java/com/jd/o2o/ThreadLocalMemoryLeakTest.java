package com.jd.o2o;

import java.util.concurrent.Callable;

import junit.framework.TestCase;

public class ThreadLocalMemoryLeakTest extends TestCase {
  public void test_threadlocal() throws InterruptedException {
    System.out.println("test_threadlocal: ");
    ThreadLocalRunnable run = new ThreadLocalRunnable();
    Thread thread = new Thread(run);
    thread.start();
    thread.join();
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
