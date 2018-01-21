package com.github.xionghuicoder.core;

import java.util.Queue;
import java.util.Set;

import com.github.xionghuicoder.Eslf4jException;
import com.github.xionghuicoder.configuration.ConfigurationVO;
import com.github.xionghuicoder.configuration.FileReader;
import com.github.xionghuicoder.core.bean.MessageBean;
import com.github.xionghuicoder.core.filter.Filter;
import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.core.queue.FixedQueue;
import com.github.xionghuicoder.helpers.LogHelper;

/**
 * 控制配置文件的加载
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class LogManager {
  public static final String CONFIGURATION_PATH = "eslf4j.properties.path";

  public static final String DEFAULT_CONFIGURATION_FILE = "eslf4j.properties";

  // 需要设置为static以便共享
  private static final ThreadLocal<FixedQueue> QUEUE_LOCAL = new ThreadLocal<FixedQueue>();

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
      throw new Eslf4jException("ConfigurationVO is null");
    }
    vo.init();
  }

  LogManager() {
    // getAndInitQueue();
    // 不能在此处初始化，比如在spring mvc框架中会反射调用构造函数，而后面getQueue的ThreadLocal会在不同的线程中，会返回null
  }

  /**
   * 获取queue，如果没有queue，则初始化queue<br>
   * 懒加载queueLocal
   *
   * @return
   */
  private FixedQueue getAndInitQueue() {
    FixedQueue queue = QUEUE_LOCAL.get();
    if (queue != null) {
      return queue;
    }
    ConfigurationVO vo = cfgVO;
    if (vo == null) {
      return null;
    }
    int count = vo.getCount();
    queue = new FixedQueue(count);
    QUEUE_LOCAL.set(queue);
    return queue;
  }

  /**
   * 判断是否需要处理level级别的数据
   * 
   * @param level level
   * @return boolean
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

  public void manage(MessageBean bean) {
    FixedQueue queue = this.getAndInitQueue();
    if (queue == null) {
      return;
    }
    queue.add(bean);
    ConfigurationVO vo = cfgVO;
    if (vo != null) {
      Set<Filter> filterSet = vo.getFilterSet();
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

  public void clearLog() {
    FixedQueue queue = this.getAndInitQueue();
    if (queue == null) {
      return;
    }
    queue.clear();
  }

  public FixedQueue getQueue() {
    FixedQueue queue = this.getAndInitQueue();
    return queue;
  }

  public static void destory() {
    cfgVO = null;
  }

  @Override
  public String toString() {
    String manager = null;
    Queue<MessageBean> queue = QUEUE_LOCAL.get();
    if (queue != null) {
      manager = queue.toString();
    }
    return manager;
  }
}
