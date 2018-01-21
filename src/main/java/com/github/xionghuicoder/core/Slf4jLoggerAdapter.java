package com.github.xionghuicoder.core;

import java.io.Serializable;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import com.github.xionghuicoder.core.bean.MessageBean;

/**
 * Slf4jLogger适配器
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Slf4jLoggerAdapter extends MarkerIgnoringBase
    implements LocationAwareLogger, Serializable {
  private static final long serialVersionUID = 3926353937224343575L;

  private final static String TAG = Slf4jLoggerAdapter.class.getName();

  private final transient Logger logger;

  private final LogManager logManager = new LogManager();

  // WARN: Slf4jLoggerAdapter constructor should have only package access so
  // that only JclSlf4jLoggerFactory be able to create one.
  Slf4jLoggerAdapter(Logger logger) {
    this.logger = logger;
    this.name = logger.getName();
  }

  /**
   * 创建{@link Slf4jLoggerAdapter Slf4jLoggerAdapter}的标记
   *
   * @param level
   * @return
   */
  private String createTag(String level) {
    StringBuilder builder = new StringBuilder();
    builder.append("[").append(TAG).append(":").append(level).append("] ");
    return builder.toString();
  }

  @Override
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }

  @Override
  public void trace(String msg) {
    if (!this.logManager.isEnabled(Level.TRACE) && !this.isTraceEnabled()) {
      return;
    }
    String message = this.createTag(Level.TRACE.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.TRACE)) {
      this.logger.trace(message);
      return;
    }
    if (this.isTraceEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.trace(m, tb);
        }
      }
      this.logger.trace(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object arg) {
    if (!this.logManager.isEnabled(Level.TRACE) && !this.isTraceEnabled()) {
      return;
    }
    String message = this.createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.TRACE)) {
      this.logger.trace(message, arg);
      return;
    }
    if (this.isTraceEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.trace(m, tb);
        }
      }
      this.logger.trace(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    if (!this.logManager.isEnabled(Level.TRACE) && !this.isTraceEnabled()) {
      return;
    }
    String message = this.createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.TRACE)) {
      this.logger.trace(message, arg1, arg2);
      return;
    }
    if (this.isTraceEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.trace(m, tb);
        }
      }
      this.logger.trace(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object... arguments) {
    if (!this.logManager.isEnabled(Level.TRACE) && !this.isTraceEnabled()) {
      return;
    }
    String message = this.createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.TRACE)) {
      this.logger.trace(message, arguments);
      return;
    }
    if (this.isTraceEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.trace(m, tb);
        }
      }
      this.logger.trace(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void trace(String msg, Throwable t) {
    if (!this.logManager.isEnabled(Level.TRACE) && !this.isTraceEnabled()) {
      return;
    }
    String message = this.createTag(Level.TRACE.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.TRACE)) {
      this.logger.trace(message, t);
      return;
    }
    if (this.isTraceEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.trace(m, tb);
        }
      }
      this.logger.trace(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }

  @Override
  public void debug(String msg) {
    if (!this.logManager.isEnabled(Level.DEBUG) && !this.isDebugEnabled()) {
      return;
    }
    String message = this.createTag(Level.DEBUG.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.DEBUG)) {
      this.logger.debug(message);
      return;
    }
    if (this.isDebugEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.debug(m, tb);
        }
      }
      this.logger.debug(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object arg) {
    if (!this.logManager.isEnabled(Level.DEBUG) && !this.isDebugEnabled()) {
      return;
    }
    String message = this.createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.DEBUG)) {
      this.logger.debug(message, arg);
      return;
    }
    if (this.isDebugEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.debug(m, tb);
        }
      }
      this.logger.debug(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    if (!this.logManager.isEnabled(Level.DEBUG) && !this.isDebugEnabled()) {
      return;
    }
    String message = this.createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.DEBUG)) {
      this.logger.debug(message, arg1, arg2);
      return;
    }
    if (this.isDebugEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.debug(m, tb);
        }
      }
      this.logger.debug(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object... arguments) {
    if (!this.logManager.isEnabled(Level.DEBUG) && !this.isDebugEnabled()) {
      return;
    }
    String message = this.createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.DEBUG)) {
      this.logger.debug(message, arguments);
      return;
    }
    if (this.isDebugEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.debug(m, tb);
        }
      }
      this.logger.debug(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void debug(String msg, Throwable t) {
    if (!this.logManager.isEnabled(Level.DEBUG) && !this.isDebugEnabled()) {
      return;
    }
    String message = this.createTag(Level.DEBUG.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.DEBUG)) {
      this.logger.debug(message, t);
      return;
    }
    if (this.isDebugEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.debug(m, tb);
        }
      }
      this.logger.debug(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }

  @Override
  public void info(String msg) {
    if (!this.logManager.isEnabled(Level.INFO) && !this.isInfoEnabled()) {
      return;
    }
    String message = this.createTag(Level.INFO.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.INFO)) {
      this.logger.info(message);
      return;
    }
    if (this.isInfoEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.info(m, tb);
        }
      }
      this.logger.info(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object arg) {
    if (!this.logManager.isEnabled(Level.INFO) && !this.isInfoEnabled()) {
      return;
    }
    String message = this.createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.INFO)) {
      this.logger.info(message, arg);
      return;
    }
    if (this.isInfoEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.info(m, tb);
        }
      }
      this.logger.info(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    if (!this.logManager.isEnabled(Level.INFO) && !this.isInfoEnabled()) {
      return;
    }
    String message = this.createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.INFO)) {
      this.logger.info(message, arg1, arg2);
      return;
    }
    if (this.isInfoEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.info(m, tb);
        }
      }
      this.logger.info(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object... arguments) {
    if (!this.logManager.isEnabled(Level.INFO) && !this.isInfoEnabled()) {
      return;
    }
    String message = this.createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.INFO)) {
      this.logger.info(message, arguments);
      return;
    }
    if (this.isInfoEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.info(m, tb);
        }
      }
      this.logger.info(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void info(String msg, Throwable t) {
    if (!this.logManager.isEnabled(Level.INFO) && !this.isInfoEnabled()) {
      return;
    }
    String message = this.createTag(Level.INFO.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.INFO)) {
      this.logger.info(message, t);
      return;
    }
    if (this.isInfoEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.info(m, tb);
        }
      }
      this.logger.info(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public boolean isWarnEnabled() {
    return this.logger.isWarnEnabled();
  }

  @Override
  public void warn(String msg) {
    if (!this.logManager.isEnabled(Level.WARN) && !this.isWarnEnabled()) {
      return;
    }
    String message = this.createTag(Level.WARN.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.WARN)) {
      this.logger.warn(message);
      return;
    }
    if (this.isWarnEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.warn(m, tb);
        }
      }
      this.logger.warn(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object arg) {
    if (!this.logManager.isEnabled(Level.WARN) && !this.isWarnEnabled()) {
      return;
    }
    String message = this.createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.WARN)) {
      this.logger.warn(message, arg);
      return;
    }
    if (this.isWarnEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.warn(m, tb);
        }
      }
      this.logger.warn(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    if (!this.logManager.isEnabled(Level.WARN) && !this.isWarnEnabled()) {
      return;
    }
    String message = this.createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.WARN)) {
      this.logger.warn(message, arg1, arg2);
      return;
    }
    if (this.isWarnEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.warn(m, tb);
        }
      }
      this.logger.warn(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object... arguments) {
    if (!this.logManager.isEnabled(Level.WARN) && !this.isWarnEnabled()) {
      return;
    }
    String message = this.createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.WARN)) {
      this.logger.warn(message, arguments);
      return;
    }
    if (this.isWarnEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.warn(m, tb);
        }
      }
      this.logger.warn(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void warn(String msg, Throwable t) {
    if (!this.logManager.isEnabled(Level.WARN) && !this.isWarnEnabled()) {
      return;
    }
    String message = this.createTag(Level.WARN.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.WARN)) {
      this.logger.warn(message, t);
      return;
    }
    if (this.isWarnEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.warn(m, tb);
        }
      }
      this.logger.warn(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public boolean isErrorEnabled() {
    return this.logger.isErrorEnabled();
  }

  @Override
  public void error(String msg) {
    if (!this.logManager.isEnabled(Level.ERROR) && !this.isErrorEnabled()) {
      return;
    }
    String message = this.createTag(Level.ERROR.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.ERROR)) {
      this.logger.error(message);
      return;
    }
    if (this.isErrorEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.error(m, tb);
        }
      }
      this.logger.error(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object arg) {
    if (!this.logManager.isEnabled(Level.ERROR) && !this.isErrorEnabled()) {
      return;
    }
    String message = this.createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.ERROR)) {
      this.logger.error(message, arg);
      return;
    }
    if (this.isErrorEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.error(m, tb);
        }
      }
      this.logger.error(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    if (!this.logManager.isEnabled(Level.ERROR) && !this.isErrorEnabled()) {
      return;
    }
    String message = this.createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.ERROR)) {
      this.logger.error(message, arg1, arg2);
      return;
    }
    if (this.isErrorEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.error(m, tb);
        }
      }
      this.logger.error(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object... arguments) {
    if (!this.logManager.isEnabled(Level.ERROR) && !this.isErrorEnabled()) {
      return;
    }
    String message = this.createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!this.logManager.isEnabled(Level.ERROR)) {
      this.logger.error(message, arguments);
      return;
    }
    if (this.isErrorEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.error(m, tb);
        }
      }
      this.logger.error(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void error(String msg, Throwable t) {
    if (!this.logManager.isEnabled(Level.ERROR) && !this.isErrorEnabled()) {
      return;
    }
    String message = this.createTag(Level.ERROR.getName()) + (msg == null ? "" : msg);
    if (!this.logManager.isEnabled(Level.ERROR)) {
      this.logger.error(message, t);
      return;
    }
    if (this.isErrorEnabled()) {
      Queue<MessageBean> msgQueue = this.logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          this.logger.error(m, tb);
        }
      }
      this.logger.error(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      this.logManager.manage(bean);
    }
  }

  @Override
  public void log(Marker marker, String fqcn, int level, String message, Object[] argArray,
      Throwable t) {
    if (!(this.logger instanceof LocationAwareLogger)) {
      throw new UnsupportedOperationException();
    }
    ((LocationAwareLogger) this.logger).log(marker, fqcn, level, message, argArray, t);
  }

  public void clearLog() {
    this.logManager.clearLog();
  }
}
