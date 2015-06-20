package com.jd.o2o.helpers;

/**
 * 用于打印eslf4j的日志
 *
 * @author xionghui
 * @date 2015年6月13日
 *
 */
public class LogHelper {
  public static final String DEBUG_KEY = "elsf4j.debug";

  private static boolean debugEnabled = false;

  /**
   * quiet模式下任何日志不输出
   */
  private static boolean quietMode = false;

  private static final String PREFIX = "elsf4j: ";
  private static final String ERR_PREFIX = "elsf4j:ERROR ";
  private static final String WARN_PREFIX = "esl4j:WARN ";

  static {
    String key = System.getProperty(DEBUG_KEY);
    if (key != null) {
      key = key.trim();
      if (!"false".equalsIgnoreCase(key)) {
        debugEnabled = true;
      }
    }
  }

  /**
   * 开启debug日志
   */
  public static void setInternalDebugging(boolean enabled) {
    debugEnabled = enabled;
  }

  public static void setQuietMode(boolean quietMode) {
    LogHelper.quietMode = quietMode;
  }

  public static void debug(String msg) {
    if (debugEnabled && !quietMode) {
      System.out.println(PREFIX + msg);
    }
  }

  public static void debug(String msg, Throwable t) {
    if (debugEnabled && !quietMode) {
      System.out.println(PREFIX + msg);
      if (t != null)
        t.printStackTrace(System.out);
    }
  }

  public static void warn(String msg) {
    if (quietMode) {
      return;
    }
    System.err.println(WARN_PREFIX + msg);
  }

  public static void warn(String msg, Throwable t) {
    if (quietMode) {
      return;
    }
    System.err.println(WARN_PREFIX + msg);
    if (t != null) {
      t.printStackTrace();
    }
  }

  public static void error(String msg) {
    if (quietMode) {
      return;
    }
    System.err.println(ERR_PREFIX + msg);
  }

  public static void error(String msg, Throwable t) {
    if (quietMode) {
      return;
    }
    System.err.println(ERR_PREFIX + msg);
    if (t != null) {
      t.printStackTrace();
    }
  }
}
