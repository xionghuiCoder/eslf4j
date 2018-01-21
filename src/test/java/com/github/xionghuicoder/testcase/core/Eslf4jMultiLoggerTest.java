package com.github.xionghuicoder.testcase.core;

import org.junit.Test;
import org.slf4j.Logger;

import com.github.xionghuicoder.core.Eslf4jLoggerFactory;

import junit.framework.TestCase;

public class Eslf4jMultiLoggerTest extends TestCase {

  @Test
  public void testMultiLogger() {
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
