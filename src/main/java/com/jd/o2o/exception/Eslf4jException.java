package com.jd.o2o.exception;

/**
 * 异常处理
 *
 * @author xionghui
 * @date 2015年6月12日
 *
 */
public class Eslf4jException extends RuntimeException {
  private static final long serialVersionUID = 3105124842960704328L;

  public Eslf4jException() {
    super();
  }

  public Eslf4jException(String message) {
    super(message);
  }

  public Eslf4jException(Throwable cause) {
    super(cause);
  }

  public Eslf4jException(String message, Throwable cause) {
    super(message, cause);
  }
}
