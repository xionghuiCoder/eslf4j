package com.jd.o2o.exception;

/**
 * 加载配置文件异常
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class Eslf4jConfigurationException extends RuntimeException {
  private static final long serialVersionUID = -3283629311515497509L;

  public Eslf4jConfigurationException() {
    super();
  }

  public Eslf4jConfigurationException(String message) {
    super(message);
  }

  public Eslf4jConfigurationException(Throwable cause) {
    super(cause);
  }

  public Eslf4jConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }
}
