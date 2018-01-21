package com.github.xionghuicoder.testcase;

import org.junit.Test;
import org.slf4j.Logger;

import com.github.xionghuicoder.core.Eslf4jLoggerFactory;

import junit.framework.TestCase;

public class Slf4jLevelLogTest extends TestCase {
  private final Logger logger = Eslf4jLoggerFactory.getLogger(this.getClass());

  @Test
  public void testSlf4j() {
    this.logger.info("1");
    this.logger.info("12");
    this.logger.info("123");
    this.logger.info("1234");
    this.logger.info("12345");
    this.logger.error("123456");
  }
}
