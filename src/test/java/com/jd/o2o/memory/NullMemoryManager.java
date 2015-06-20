package com.jd.o2o.memory;

import com.jd.o2o.core.memory.IMemoryManager;
import com.jd.o2o.core.queue.FixedQueue;

/**
 * 空MemoryManager，用于测试加载配置文件
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class NullMemoryManager implements IMemoryManager {
  @Override
  public void manager(FixedQueue queue) {}
}
