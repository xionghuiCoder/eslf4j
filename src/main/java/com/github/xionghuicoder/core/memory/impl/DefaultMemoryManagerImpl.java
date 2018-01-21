package com.github.xionghuicoder.core.memory.impl;

import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.core.queue.FixedQueue;

/**
 * MemoryManager的默认实现，直接清除FixedQueue释放内存
 * 
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultMemoryManagerImpl implements IMemoryManager {

  @Override
  public void manager(FixedQueue queue) {
    queue.clear();
  }
}
