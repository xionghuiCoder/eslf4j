package com.jd.o2o.core.memory.impl;

import com.jd.o2o.core.memory.IMemoryManager;
import com.jd.o2o.core.queue.FixedQueue;

/**
 * MemoryManager的默认实现，直接清除FixedQueue释放内存
 *
 * @author xionghui
 * @date 2015年6月17日
 *
 */
public class DefaultMemoryManagerImpl implements IMemoryManager {

  @Override
  public void manager(FixedQueue queue) {
    queue.clear();
  }
}
