package com.github.xionghuicoder.core;

import com.github.xionghuicoder.Eslf4jException;

/**
 * 日志级别
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Level {
  ALL(Integer.MIN_VALUE, "ALL"), //
  TRACE(5000, "TRACE"), //
  DEBUG(10000, "DEBUG"), //
  INFO(20000, "INFO"), //
  WARN(30000, "WARN"), //
  ERROR(40000, "ERROR"), //
  FATAL(50000, "FATAL"), //
  OFF(Integer.MAX_VALUE, "OFF"), //
  ;

  private final int value;
  private final String name;

  Level(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public static Level getLevelByValue(String levelValue) {
    String value = null;
    if (levelValue != null) {
      value = levelValue.toUpperCase();
    }
    if (ALL.name.equals(value)) {
      return ALL;
    } else if (TRACE.name.equals(value)) {
      return TRACE;
    } else if (DEBUG.name.equals(value)) {
      return DEBUG;
    } else if (INFO.name.equals(value)) {
      return INFO;
    } else if (WARN.name.equals(value)) {
      return WARN;
    } else if (ERROR.name.equals(value)) {
      return ERROR;
    } else if (FATAL.name.equals(value)) {
      return FATAL;
    } else if (OFF.name.equals(value)) {
      return OFF;
    }
    throw new Eslf4jException("invalid level");
  }

  public boolean isEnabled(Level level) {
    return this.value <= level.value;
  }
}
