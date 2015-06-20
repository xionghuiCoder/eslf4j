package com.jd.o2o.configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashSet;
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
  private transient Set<Filter> filterSet;
  private transient IMemoryManager memoryManager;

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

  public Set<Filter> getFilterSet() {
    return filterSet;
  }

  public void setFilterSet(Set<Filter> filterSet) {
    this.filterSet = filterSet;
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

  /**
   * 序列化对象
   * 
   * @param s
   */
  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    s.writeInt(count);
    s.writeLong(bufferSize);
    s.writeObject(minThreshold);
    if (filterSet != null) {
      s.writeInt(filterSet.size());
      for (Filter filter : filterSet) {
        s.writeObject(filter.getClass());
      }
    } else {
      s.writeInt(-1);
    }
    s.writeObject(memoryManager.getClass());
  }

  /**
   * 反序列化对象
   * 
   * @param s
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException,
      InstantiationException, IllegalAccessException {
    s.defaultReadObject();
    count = s.readInt();
    bufferSize = s.readLong();
    minThreshold = (Level) s.readObject();
    int size = s.readInt();
    if (size != -1) {
      // 默认使用LinkedHashSet保证filter的顺序
      filterSet = new LinkedHashSet<Filter>();
      for (int i = 0; i < size; i++) {
        Class<?> filterClazz = (Class<?>) s.readObject();
        filterSet.add((Filter) filterClazz.newInstance());
      }
    }
    Class<?> memoryManagerClazz = (Class<?>) s.readObject();
    memoryManager = (IMemoryManager) memoryManagerClazz.newInstance();
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    String memoryStr = super.toString();
    builder.append(memoryStr).append("\n");
    builder.append("count: ").append(count).append("\n");
    builder.append("bufferSize: ").append(bufferSize).append("\n");
    builder.append("minThreshold: ").append(minThreshold).append("\n");
    builder.append("filterSet: ").append(filterSet).append("\n");
    builder.append("memoryManager: ").append(memoryManager);
    return builder.toString();
  }
}
