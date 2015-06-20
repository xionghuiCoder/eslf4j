package com.jd.o2o.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.o2o.exception.Eslf4jException;

/**
 * LoggerFactory门面
 *
 * @author xionghui
 * @date 2015年6月16日
 *
 */
public final class Eslf4jLoggerFactory {
  private static final ConcurrentMap<String, Logger> LOGGER_MAP =
      new ConcurrentHashMap<String, Logger>();

  static {
    /**
     * 解决LoggerFactory的getILoggerFactory()的并发问题, please @see LoggerFactory.INITIALIZATION_STATE
     */
    LoggerFactory.getILoggerFactory();
  }

  // private constructor prevents instantiation
  private Eslf4jLoggerFactory() {
    throw new Eslf4jException("we should not create the instance of " + this.getClass().getName());
  }

  public static Logger getLogger(String name) {
    Logger logger = LOGGER_MAP.get(name);
    if (logger == null) {
      ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
      Logger slf4jLogger = iLoggerFactory.getLogger(name);
      logger = new Slf4jLoggerAdapter(slf4jLogger);
      Logger oldLogger = LOGGER_MAP.putIfAbsent(name, logger);
      if (oldLogger != null) {
        logger = oldLogger;
      }
    }
    return logger;
  }

  public static Logger getLogger(Class<?> clazz) {
    return getLogger(clazz.getName());
  }

  /**
   * 释放内存，防止内存泄露
   */
  public static void destroy() {
    LogManager.destory();
    LOGGER_MAP.clear();
  }
}
