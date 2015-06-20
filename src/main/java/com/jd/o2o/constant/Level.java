package com.jd.o2o.constant;

import com.jd.o2o.exception.Eslf4jConfigurationException;

/**
 * 日志级别
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public enum Level {
  ALL(Integer.MIN_VALUE, "ALL"), TRACE(5000, "TRACE"), DEBUG(10000, "DEBUG"), INFO(20000, "INFO"), WARN(
      30000, "WARN"), ERROR(40000, "ERROR"), FATAL(50000, "FATAL"), OFF(Integer.MAX_VALUE, "OFF");

  private final int value;
  private final String name;

  Level(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public String getName() {
    return name;
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
    throw new Eslf4jConfigurationException("invalid level");
  }

  public boolean isEnabled(Level level) {
    return value <= level.value;
  }
}
