package com.github.xionghuicoder;

/**
 * eslf4j异常类
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class Eslf4jException extends RuntimeException {
  private static final long serialVersionUID = -3283629311515497509L;

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
