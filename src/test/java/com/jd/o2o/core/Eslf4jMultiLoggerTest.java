package com.jd.o2o.core;

import junit.framework.TestCase;

import org.slf4j.Logger;

/**
 * 测试同一线程下使用多个logger
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
public class Eslf4jMultiLoggerTest extends TestCase {

  public void test_multiLogger() {
    Logger logger = Eslf4jLoggerFactory.getLogger("first");
    logger.trace("trace");
    logger.info("info1");
    logger.info("info2");
    logger.debug("debug");
    logger = Eslf4jLoggerFactory.getLogger("second");
    logger.trace("another trace");
    logger.info("another info1");
    logger.info("another info2");
    logger.debug("another debug");
    logger.error("another error");
    logger = Eslf4jLoggerFactory.getLogger("first");
    logger.error("error");
  }
}
