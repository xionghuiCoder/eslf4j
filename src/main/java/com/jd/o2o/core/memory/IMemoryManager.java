package com.jd.o2o.core.memory;

import com.jd.o2o.core.queue.FixedQueue;

/**
 * 内存管理接口，当内存占用大于buffersize时用于控制memory内存
 *
 * @author xionghui
 * @date 2015年6月17日
 *
 */
public interface IMemoryManager {
  public void manager(FixedQueue queue);
}
