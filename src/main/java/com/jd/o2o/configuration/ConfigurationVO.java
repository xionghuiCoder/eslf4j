package com.jd.o2o.configuration;

import java.io.Serializable;
import java.util.Set;

import com.jd.o2o.constant.Level;
import com.jd.o2o.core.filter.Filter;
import com.jd.o2o.core.memory.IMemoryManager;
import com.jd.o2o.core.memory.impl.DefaultMemoryManagerImpl;
import com.jd.o2o.exception.Eslf4jConfigurationException;

/**
 * 配置VO
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class ConfigurationVO implements Serializable {
  private static final long serialVersionUID = 770590344995331324L;

  private int count;
  private long bufferSize;
  private Level minThreshold;
  private Set<Filter> filter;
  private IMemoryManager memoryManager;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public long getBufferSize() {
    return bufferSize;
  }

  public void setBufferSize(long bufferSize) {
    this.bufferSize = bufferSize;
  }

  public Level getMinThreshold() {
    return minThreshold;
  }

  public void setMinThreshold(Level minThreshold) {
    this.minThreshold = minThreshold;
  }

  public Set<Filter> getFilter() {
    return filter;
  }

  public void setFilter(Set<Filter> filter) {
    this.filter = filter;
  }

  public IMemoryManager getMemoryManager() {
    return memoryManager;
  }

  public void setMemoryManager(IMemoryManager memoryManager) {
    this.memoryManager = memoryManager;
  }

  public void init() {
    if (count <= 0) {
      throw new Eslf4jConfigurationException("count should be positive");
    }
    if (bufferSize <= 0) {
      throw new Eslf4jConfigurationException("bufferSize should be positive");
    }
    if (minThreshold == null) {
      throw new Eslf4jConfigurationException("minThreshold should not be null");
    }
    // memoryManager默认实现
    if (memoryManager == null) {
      memoryManager = new DefaultMemoryManagerImpl();
    }
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    String memoryStr = super.toString();
    builder.append(memoryStr).append("\n");
    builder.append("count: ").append(count).append("\n");
    builder.append("bufferSize: ").append(bufferSize).append("\n");
    builder.append("minThreshold: ").append(minThreshold).append("\n");
    builder.append("filter: ").append(filter).append("\n");
    builder.append("memoryManager: ").append(memoryManager);
    return builder.toString();
  }
}
