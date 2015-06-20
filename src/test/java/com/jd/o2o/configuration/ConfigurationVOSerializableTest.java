package com.jd.o2o.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.TestCase;

import com.jd.o2o.constant.Level;
import com.jd.o2o.core.filter.Filter;
import com.jd.o2o.filter.Null1Filter;
import com.jd.o2o.filter.Null2Filter;
import com.jd.o2o.memory.NullMemoryManager;

/**
 * 测试ConfigurationVO的序列化和反序列化
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
public class ConfigurationVOSerializableTest extends TestCase {
  private static final String FILE_NAME = ".seria";

  public void test_load() throws Exception {
    ConfigurationVO cfgVO = new ConfigurationVO();
    cfgVO.setCount(5);
    cfgVO.setBufferSize(100);
    cfgVO.setMinThreshold(Level.INFO);
    Set<Filter> filterSet = new LinkedHashSet<Filter>();
    filterSet.add(new Null1Filter());
    filterSet.add(new Null2Filter());
    cfgVO.setFilterSet(filterSet);
    cfgVO.setMemoryManager(new NullMemoryManager());
    // System.out.println(cfgVO);
    write(cfgVO);
    cfgVO = read();
    System.out.println(cfgVO);
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
}
