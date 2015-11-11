package com.jd.o2o.core;

import java.io.Serializable;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import com.jd.o2o.constant.Level;
import com.jd.o2o.core.bean.MessageBean;

/**
 * Slf4jLogger适配器
 *
 * @author xionghui
 * @date 2015年6月17日
 *
 */
public final class Slf4jLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger,
    Serializable {
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
   * 创建{@link Slf4jLoggerAdapter}的标记
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
    return logger.isTraceEnabled();
  }

  @Override
  public void trace(String msg) {
    if (!logManager.isEnabled(Level.TRACE) && !isTraceEnabled()) {
      return;
    }
    String message = createTag(Level.TRACE.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.TRACE)) {
      logger.trace(message);
      return;
    }
    if (isTraceEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.trace(m, tb);
        }
      }
      logger.trace(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object arg) {
    if (!logManager.isEnabled(Level.TRACE) && !isTraceEnabled()) {
      return;
    }
    String message = createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.TRACE)) {
      logger.trace(message, arg);
      return;
    }
    if (isTraceEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.trace(m, tb);
        }
      }
      logger.trace(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object arg1, Object arg2) {
    if (!logManager.isEnabled(Level.TRACE) && !isTraceEnabled()) {
      return;
    }
    String message = createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.TRACE)) {
      logger.trace(message, arg1, arg2);
      return;
    }
    if (isTraceEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.trace(m, tb);
        }
      }
      logger.trace(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void trace(String format, Object... arguments) {
    if (!logManager.isEnabled(Level.TRACE) && !isTraceEnabled()) {
      return;
    }
    String message = createTag(Level.TRACE.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.TRACE)) {
      logger.trace(message, arguments);
      return;
    }
    if (isTraceEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.trace(m, tb);
        }
      }
      logger.trace(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void trace(String msg, Throwable t) {
    if (!logManager.isEnabled(Level.TRACE) && !isTraceEnabled()) {
      return;
    }
    String message = createTag(Level.TRACE.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.TRACE)) {
      logger.trace(message, t);
      return;
    }
    if (isTraceEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.trace(m, tb);
        }
      }
      logger.trace(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      logManager.manage(bean);
    }
  }

  @Override
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  @Override
  public void debug(String msg) {
    if (!logManager.isEnabled(Level.DEBUG) && !isDebugEnabled()) {
      return;
    }
    String message = createTag(Level.DEBUG.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.DEBUG)) {
      logger.debug(message);
      return;
    }
    if (isDebugEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.debug(m, tb);
        }
      }
      logger.debug(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object arg) {
    if (!logManager.isEnabled(Level.DEBUG) && !isDebugEnabled()) {
      return;
    }
    String message = createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.DEBUG)) {
      logger.debug(message, arg);
      return;
    }
    if (isDebugEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.debug(m, tb);
        }
      }
      logger.debug(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object arg1, Object arg2) {
    if (!logManager.isEnabled(Level.DEBUG) && !isDebugEnabled()) {
      return;
    }
    String message = createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.DEBUG)) {
      logger.debug(message, arg1, arg2);
      return;
    }
    if (isDebugEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.debug(m, tb);
        }
      }
      logger.debug(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void debug(String format, Object... arguments) {
    if (!logManager.isEnabled(Level.DEBUG) && !isDebugEnabled()) {
      return;
    }
    String message = createTag(Level.DEBUG.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.DEBUG)) {
      logger.debug(message, arguments);
      return;
    }
    if (isDebugEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.debug(m, tb);
        }
      }
      logger.debug(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void debug(String msg, Throwable t) {
    if (!logManager.isEnabled(Level.DEBUG) && !isDebugEnabled()) {
      return;
    }
    String message = createTag(Level.DEBUG.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.DEBUG)) {
      logger.debug(message, t);
      return;
    }
    if (isDebugEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.debug(m, tb);
        }
      }
      logger.debug(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      logManager.manage(bean);
    }
  }

  @Override
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  @Override
  public void info(String msg) {
    if (!logManager.isEnabled(Level.INFO) && !isInfoEnabled()) {
      return;
    }
    String message = createTag(Level.INFO.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.INFO)) {
      logger.info(message);
      return;
    }
    if (isInfoEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.info(m, tb);
        }
      }
      logger.info(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object arg) {
    if (!logManager.isEnabled(Level.INFO) && !isInfoEnabled()) {
      return;
    }
    String message = createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.INFO)) {
      logger.info(message, arg);
      return;
    }
    if (isInfoEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.info(m, tb);
        }
      }
      logger.info(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object arg1, Object arg2) {
    if (!logManager.isEnabled(Level.INFO) && !isInfoEnabled()) {
      return;
    }
    String message = createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.INFO)) {
      logger.info(message, arg1, arg2);
      return;
    }
    if (isInfoEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.info(m, tb);
        }
      }
      logger.info(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void info(String format, Object... arguments) {
    if (!logManager.isEnabled(Level.INFO) && !isInfoEnabled()) {
      return;
    }
    String message = createTag(Level.INFO.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.INFO)) {
      logger.info(message, arguments);
      return;
    }
    if (isInfoEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.info(m, tb);
        }
      }
      logger.info(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void info(String msg, Throwable t) {
    if (!logManager.isEnabled(Level.INFO) && !isInfoEnabled()) {
      return;
    }
    String message = createTag(Level.INFO.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.INFO)) {
      logger.info(message, t);
      return;
    }
    if (isInfoEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.info(m, tb);
        }
      }
      logger.info(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      logManager.manage(bean);
    }
  }

  @Override
  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  @Override
  public void warn(String msg) {
    if (!logManager.isEnabled(Level.WARN) && !isWarnEnabled()) {
      return;
    }
    String message = createTag(Level.WARN.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.WARN)) {
      logger.warn(message);
      return;
    }
    if (isWarnEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.warn(m, tb);
        }
      }
      logger.warn(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object arg) {
    if (!logManager.isEnabled(Level.WARN) && !isWarnEnabled()) {
      return;
    }
    String message = createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.WARN)) {
      logger.warn(message, arg);
      return;
    }
    if (isWarnEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.warn(m, tb);
        }
      }
      logger.warn(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object arg1, Object arg2) {
    if (!logManager.isEnabled(Level.WARN) && !isWarnEnabled()) {
      return;
    }
    String message = createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.WARN)) {
      logger.warn(message, arg1, arg2);
      return;
    }
    if (isWarnEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.warn(m, tb);
        }
      }
      logger.warn(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void warn(String format, Object... arguments) {
    if (!logManager.isEnabled(Level.WARN) && !isWarnEnabled()) {
      return;
    }
    String message = createTag(Level.WARN.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.WARN)) {
      logger.warn(message, arguments);
      return;
    }
    if (isWarnEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.warn(m, tb);
        }
      }
      logger.warn(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void warn(String msg, Throwable t) {
    if (!logManager.isEnabled(Level.WARN) && !isWarnEnabled()) {
      return;
    }
    String message = createTag(Level.WARN.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.WARN)) {
      logger.warn(message, t);
      return;
    }
    if (isWarnEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.warn(m, tb);
        }
      }
      logger.warn(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      logManager.manage(bean);
    }
  }

  @Override
  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  @Override
  public void error(String msg) {
    if (!logManager.isEnabled(Level.ERROR) && !isErrorEnabled()) {
      return;
    }
    String message = createTag(Level.ERROR.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.ERROR)) {
      logger.error(message);
      return;
    }
    if (isErrorEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.error(m, tb);
        }
      }
      logger.error(message);
    } else {
      MessageBean bean = new MessageBean.Builder(message).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object arg) {
    if (!logManager.isEnabled(Level.ERROR) && !isErrorEnabled()) {
      return;
    }
    String message = createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.ERROR)) {
      logger.error(message, arg);
      return;
    }
    if (isErrorEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.error(m, tb);
        }
      }
      logger.error(message, arg);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object arg1, Object arg2) {
    if (!logManager.isEnabled(Level.ERROR) && !isErrorEnabled()) {
      return;
    }
    String message = createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.ERROR)) {
      logger.error(message, arg1, arg2);
      return;
    }
    if (isErrorEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.error(m, tb);
        }
      }
      logger.error(message, arg1, arg2);
    } else {
      FormattingTuple ft = MessageFormatter.format(message, arg1, arg2);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void error(String format, Object... arguments) {
    if (!logManager.isEnabled(Level.ERROR) && !isErrorEnabled()) {
      return;
    }
    String message = createTag(Level.ERROR.getName()) + (format == null ? "" : format);
    if (!logManager.isEnabled(Level.ERROR)) {
      logger.error(message, arguments);
      return;
    }
    if (isErrorEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.error(m, tb);
        }
      }
      logger.error(message, arguments);
    } else {
      FormattingTuple ft = MessageFormatter.arrayFormat(message, arguments);
      MessageBean bean =
          new MessageBean.Builder(ft.getMessage()).throwable(ft.getThrowable()).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void error(String msg, Throwable t) {
    if (!logManager.isEnabled(Level.ERROR) && !isErrorEnabled()) {
      return;
    }
    String message = createTag(Level.ERROR.getName()) + (msg == null ? "" : msg);
    if (!logManager.isEnabled(Level.ERROR)) {
      logger.error(message, t);
      return;
    }
    if (isErrorEnabled()) {
      Queue<MessageBean> msgQueue = logManager.getQueue();
      if (msgQueue != null) {
        while (msgQueue.size() > 0) {
          MessageBean b = msgQueue.remove();
          String m = b.getMessage();
          Throwable tb = b.getThrowable();
          logger.error(m, tb);
        }
      }
      logger.error(message, t);
    } else {
      MessageBean bean = new MessageBean.Builder(message).throwable(t).build();
      logManager.manage(bean);
    }
  }

  @Override
  public void log(Marker marker, String fqcn, int level, String message, Object[] argArray,
      Throwable t) {
    if (!(logger instanceof LocationAwareLogger)) {
      throw new UnsupportedOperationException();
    }
    ((LocationAwareLogger) logger).log(marker, fqcn, level, message, argArray, t);
  }

  public void clearLog() {
    logManager.clearLog();
  }
}
