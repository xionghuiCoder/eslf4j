package com.github.xionghuicoder;

import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.core.queue.FixedQueue;

public class NullMemoryManager implements IMemoryManager {

  @Override
  public void manager(FixedQueue queue) {
    // do nothing
  }
}
