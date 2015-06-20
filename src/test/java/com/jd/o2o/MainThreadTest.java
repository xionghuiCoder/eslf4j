package com.jd.o2o;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.TestCase;

/**
 * 测试main函数和junit线程处理的区别
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
public class MainThreadTest extends TestCase {

  public static void main(String[] args) {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println("Timer thread is running...");
      }

    }, 1000, 1000);
    System.out.println("Main thread ends");
  }

  public void test() throws InterruptedException {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println("Timer thread is running...");
      }

    }, 1000, 1000);
    System.out.println("Main thread ends");
  }
}
