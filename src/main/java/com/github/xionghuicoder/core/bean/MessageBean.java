package com.github.xionghuicoder.core.bean;

import java.io.Serializable;

/**
 * 消息bean
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class MessageBean implements Serializable {
  private static final long serialVersionUID = 5070176044761727162L;

  private String message;
  private Throwable throwable;

  private MessageBean(Builder builder) {
    this.message = builder.message;
    this.throwable = builder.throwable;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Throwable getThrowable() {
    return this.throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    String beanStr = super.toString();
    builder.append(beanStr).append("\n");
    builder.append("message: ").append(this.message).append("\n");
    builder.append("throwable: ").append(this.throwable);
    return builder.toString();
  }

  /**
   * 构建器
   * 
   * @author xionghui
   * @version 1.0.0
   * @since 1.0.0
   */
  public static class Builder {
    private String message;
    private Throwable throwable;

    public Builder(String message) {
      this.message = message;
    }

    public Builder throwable(Throwable throwable) {
      this.throwable = throwable;
      return this;
    }

    public MessageBean build() {
      return new MessageBean(this);
    }
  }
}
