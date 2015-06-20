package com.jd.o2o;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4jLog test
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
public class Slf4jLogTest extends TestCase {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  public void test_slf4j() {
    logger.info(null);
    logger.info("123{}{}");
    logger.info("123{}{}", (Object) null);
    logger.info("123{}{}", (Throwable) null);
    logger.info("123{}{}", null, null);
    logger.info("123{}{}", "sad", null);
    logger.info("123{}");
    Object[] params = {"abc"};
    logger.info("123{}{}", params);
    Object[] anoParams = {"abc", "def"};
    logger.info("123{}{}", anoParams);
    Object[] noneParams = null;
    logger.info("123{}{}", noneParams);
  }
}
