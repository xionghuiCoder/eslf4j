package com.github.xionghuicoder.configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.xionghuicoder.Eslf4jException;
import com.github.xionghuicoder.core.Level;
import com.github.xionghuicoder.core.filter.Filter;
import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.core.memory.impl.DefaultMemoryManagerImpl;

/**
 * 配置VO
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigurationVO implements Serializable {
  private static final long serialVersionUID = 770590344995331324L;

  private int count;
  private long bufferSize;
  private Level minThreshold;
  private transient Set<Filter> filterSet;
  private transient IMemoryManager memoryManager;

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public long getBufferSize() {
    return this.bufferSize;
  }

  public void setBufferSize(long bufferSize) {
    this.bufferSize = bufferSize;
  }

  public Level getMinThreshold() {
    return this.minThreshold;
  }

  public void setMinThreshold(Level minThreshold) {
    this.minThreshold = minThreshold;
  }

  public Set<Filter> getFilterSet() {
    return this.filterSet;
  }

  public void setFilterSet(Set<Filter> filterSet) {
    this.filterSet = filterSet;
  }

  public IMemoryManager getMemoryManager() {
    return this.memoryManager;
  }

  public void setMemoryManager(IMemoryManager memoryManager) {
    this.memoryManager = memoryManager;
  }

  public void init() {
    if (this.count <= 0) {
      throw new Eslf4jException("count should be positive");
    }
    if (this.bufferSize <= 0) {
      throw new Eslf4jException("bufferSize should be positive");
    }
    if (this.minThreshold == null) {
      throw new Eslf4jException("minThreshold should not be null");
    }
    // memoryManager默认实现
    if (this.memoryManager == null) {
      this.memoryManager = new DefaultMemoryManagerImpl();
    }
  }

  /**
   * 序列化对象
   *
   * @param s ObjectOutputStream
   * @throws IOException IO异常
   */
  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    if (this.filterSet != null) {
      s.writeInt(this.filterSet.size());
      for (Filter filter : this.filterSet) {
        if (filter == null) {
          s.writeBoolean(false);
        } else {
          s.writeBoolean(true);
          s.writeObject(filter.getClass());
        }
      }
    } else {
      s.writeInt(-1);
    }
    if (this.memoryManager == null) {
      s.writeBoolean(false);
    } else {
      s.writeBoolean(true);
      s.writeObject(this.memoryManager.getClass());
    }
  }

  /**
   * 反序列化对象
   *
   * @param s ObjectOutputStream
   * @throws IOException IO异常
   * @throws ClassNotFoundException ClassNotFoundException
   * @throws InstantiationException InstantiationException
   * @throws IllegalAccessException IllegalAccessException
   */
  private void readObject(ObjectInputStream s)
      throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    s.defaultReadObject();
    int size = s.readInt();
    if (size != -1) {
      // 默认使用LinkedHashSet保证filter的顺序
      this.filterSet = new LinkedHashSet<Filter>();
      for (int i = 0; i < size; i++) {
        Filter filter = null;
        boolean sign = s.readBoolean();
        if (sign) {
          Class<?> filterClazz = (Class<?>) s.readObject();
          filter = (Filter) filterClazz.newInstance();
        }
        this.filterSet.add(filter);
      }
    }
    boolean sign = s.readBoolean();
    if (sign) {
      Class<?> memoryManagerClazz = (Class<?>) s.readObject();
      this.memoryManager = (IMemoryManager) memoryManagerClazz.newInstance();
    }
  }

  @Override
  public String toString() {
    return "ConfigurationVO [count=" + this.count + ", bufferSize=" + this.bufferSize
        + ", minThreshold=" + this.minThreshold + "]";
  }
}
