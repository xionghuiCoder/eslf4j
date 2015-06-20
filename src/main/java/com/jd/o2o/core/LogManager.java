package com.jd.o2o.core;

import java.util.Queue;
import java.util.Set;

import com.jd.o2o.configuration.ConfigurationVO;
import com.jd.o2o.configuration.FileReader;
import com.jd.o2o.constant.Level;
import com.jd.o2o.core.bean.MessageBean;
import com.jd.o2o.core.filter.Filter;
import com.jd.o2o.core.memory.IMemoryManager;
import com.jd.o2o.core.queue.FixedQueue;
import com.jd.o2o.exception.Eslf4jConfigurationException;
import com.jd.o2o.helpers.LogHelper;

/**
 * 控制配置文件的加载，后面可能需要加入xml加载
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class LogManager {
  public static final String CONFIGURATION_PATH = "eslf4j.properties.path";

  public static final String DEFAULT_CONFIGURATION_FILE = "eslf4j.properties";

  private final ThreadLocal<FixedQueue> queueLocal = new ThreadLocal<FixedQueue>();

  private static ConfigurationVO cfgVO;

  static {
    String path = System.getProperty(CONFIGURATION_PATH);
    if (path == null) {
      path = DEFAULT_CONFIGURATION_FILE;
    } else {
      path = path.trim();
      LogHelper.debug("Using path [" + path + "] for eslf4j configuration.");
    }
    cfgVO = FileReader.loadFile(path);
    ConfigurationVO vo = cfgVO;
    if (vo == null) {
      throw new Eslf4jConfigurationException("ConfigurationVO is null");
    }
    vo.init();
  }

  LogManager() {
    ConfigurationVO vo = cfgVO;
    if (vo == null) {
      return;
    }
    int count = vo.getCount();
    FixedQueue queue = new FixedQueue(count);
    queueLocal.set(queue);
  }

  /**
   * 判断是否需要处理level级别的数据
   * 
   * @param level
   * @return
   */
  public boolean isEnabled(Level level) {
    boolean enable = false;
    ConfigurationVO vo = cfgVO;
    if (vo != null) {
      Level threshold = vo.getMinThreshold();
      enable = threshold.isEnabled(level);
    }
    return enable;
  }

  public void manager(MessageBean bean) {
    FixedQueue queue = queueLocal.get();
    queue.add(bean);
    ConfigurationVO vo = cfgVO;
    if (vo != null) {
      Set<Filter> filterSet = vo.getFilter();
      if (filterSet != null) {
        for (Filter filter : filterSet) {
          boolean ifContinue = filter.doFilter(bean);
          if (!ifContinue) {
            return;
          }
        }
      }
      long total = FixedQueue.getCharTotal();
      long bufferSize = vo.getBufferSize();
      if (total >= bufferSize) {
        IMemoryManager manager = vo.getMemoryManager();
        manager.manager(queue);
      }
    }
  }

  public Queue<MessageBean> getQueue() {
    Queue<MessageBean> queue = queueLocal.get();
    return queue;
  }

  public static void destory() {
    cfgVO = null;
  }

  public String toString() {
    String manager = null;
    Queue<MessageBean> queue = queueLocal.get();
    if (queue != null) {
      manager = queue.toString();
    }
    return manager;
  }
}
