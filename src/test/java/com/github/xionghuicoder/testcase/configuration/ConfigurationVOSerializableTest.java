package com.github.xionghuicoder.testcase.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.github.xionghuicoder.NullMemoryManager;
import com.github.xionghuicoder.configuration.ConfigurationVO;
import com.github.xionghuicoder.core.Level;
import com.github.xionghuicoder.core.filter.Filter;
import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.filter.Null1Filter;
import com.github.xionghuicoder.filter.Null2Filter;

import junit.framework.TestCase;

public class ConfigurationVOSerializableTest extends TestCase {
  private static final String FILE_NAME = ".seria";

  @Test
  public void testLoad() throws Exception {
    ConfigurationVO cfgVO = new ConfigurationVO();
    cfgVO.setCount(5);
    cfgVO.setBufferSize(100);
    cfgVO.setMinThreshold(Level.INFO);
    Set<Filter> filterSet = new LinkedHashSet<Filter>();
    filterSet.add(new Null1Filter());
    filterSet.add(new Null2Filter());
    cfgVO.setFilterSet(filterSet);
    IMemoryManager memoryManager = new NullMemoryManager();
    cfgVO.setMemoryManager(memoryManager);
    // System.out.println(cfgVO);
    this.write(cfgVO);
    ConfigurationVO newCfgVO = this.read();
    assert cfgVO.getCount() == newCfgVO.getCount()
        && cfgVO.getBufferSize() == newCfgVO.getBufferSize()
        && cfgVO.getMinThreshold() == newCfgVO.getMinThreshold();
    IMemoryManager newMemoryManager = newCfgVO.getMemoryManager();
    assert memoryManager.getClass().equals(newMemoryManager.getClass());
  }

  private void write(ConfigurationVO cfgVO) throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
    out.writeObject(cfgVO);
    out.close();
  }

  private ConfigurationVO read() throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
    ConfigurationVO cfgVO = (ConfigurationVO) in.readObject();
    in.close();
    return cfgVO;
  }

  @Override
  public void tearDown() throws Exception {
    File file = new File(FILE_NAME);
    file.delete();
  }
}
