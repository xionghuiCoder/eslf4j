package com.github.xionghuicoder.testcase;

import org.junit.Test;
import org.slf4j.Logger;

import com.github.xionghuicoder.core.Eslf4jLoggerFactory;

import junit.framework.TestCase;

public class Slf4jLogTest extends TestCase {
  private final Logger logger = Eslf4jLoggerFactory.getLogger(this.getClass());

  @Test
  public void testSlf4j() {
    this.logger.error(null);
    this.logger.error("1{}{}");
    this.logger.error("12{}{}", (Object) null);
    this.logger.error("123{}{}", (Throwable) null);
    this.logger.error("1234{}{}", null, null);
    this.logger.error("12345{}{}", "sad", null);
    this.logger.error("123456{}");
    Object[] params = {"abc"};
    this.logger.error("1234567{}{}", params);
    Object[] anoParams = {"abc", "def"};
    this.logger.error("12345678{}{}", anoParams);
    Object[] noneParams = null;
    this.logger.error("123456789{}{}", noneParams);
  }
}
