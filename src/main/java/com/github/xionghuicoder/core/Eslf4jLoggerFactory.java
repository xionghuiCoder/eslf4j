package com.github.xionghuicoder.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.xionghuicoder.Eslf4jException;

/**
 * LoggerFactory门面
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Eslf4jLoggerFactory {
  private static final ConcurrentMap<String, Logger> LOGGER_MAP =
      new ConcurrentHashMap<String, Logger>();

  static {
    /*
     * 解决LoggerFactory的getILoggerFactory()的并发问题
     */
    LoggerFactory.getILoggerFactory();
  }

  private Eslf4jLoggerFactory() {
    throw new Eslf4jException(
        "we should not create the instance of " + Eslf4jLoggerFactory.class.getName());
  }

  public static Logger getLogger(String name) {
    Logger logger = LOGGER_MAP.get(name);
    if (logger == null) {
      Logger slf4jLogger = LoggerFactory.getLogger(name);
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
