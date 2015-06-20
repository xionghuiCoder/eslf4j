package com.jd.o2o.core;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

import org.slf4j.Logger;

/**
 * 开启多个线程测试info和error
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
public class Slf4jLoggerAdapterTest extends TestCase {
  private static CountDownLatch latch = new CountDownLatch(2);

  public void test_infoError() throws InterruptedException {
    Runnable run = new LogInfoRunnbale();
    Thread thread = new Thread(run);
    thread.start();
    run = new LogErrorRunnbale();
    thread = new Thread(run);
    thread.start();
    latch.await();
  }

  private static class LogInfoRunnbale implements Runnable {
    @Override
    public void run() {
      Logger logger = Eslf4jLoggerFactory.getLogger(this.getClass());
      System.out.println(this.getClass() + " begin");
      logger.trace(this.getClass() + "trace");
      logger.info(this.getClass() + "info1");
      logger.info(this.getClass() + "info2");
      logger.info(this.getClass() + "info3");
      logger.debug(this.getClass() + "debug");
      System.out.println(this.getClass() + " end");
      latch.countDown();
    }
  }

  private static class LogErrorRunnbale implements Runnable {
    @Override
    public void run() {
      Logger logger = Eslf4jLoggerFactory.getLogger(this.getClass());
      System.out.println(this.getClass() + " begin");
      logger.trace(this.getClass() + "trace");
      logger.info(this.getClass() + "info1");
      logger.info(this.getClass() + "info2");
      logger.error(this.getClass() + "info3");
      logger.debug(this.getClass() + "debug");
      System.out.println(this.getClass() + " end");
      latch.countDown();
    }
  }
}
