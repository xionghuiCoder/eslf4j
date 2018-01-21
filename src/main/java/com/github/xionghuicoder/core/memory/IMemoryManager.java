package com.github.xionghuicoder.core.memory;

import com.github.xionghuicoder.core.queue.FixedQueue;

/**
 * 内存管理接口，当内存占用大于buffersize时用于控制memory内存
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IMemoryManager {

  void manager(FixedQueue queue);
}
