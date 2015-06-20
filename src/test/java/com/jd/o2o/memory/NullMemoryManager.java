package com.jd.o2o.memory;

import com.jd.o2o.core.memory.IMemoryManager;
import com.jd.o2o.core.queue.FixedQueue;

public class NullMemoryManager implements IMemoryManager {
  @Override
  public void manager(FixedQueue queue) {}
}
