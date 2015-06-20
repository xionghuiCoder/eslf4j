package com.jd.o2o.configuration;

import junit.framework.TestCase;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * 测试文件加载
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class FileReaderTest extends TestCase {

  public void test_load() {
    String path = "filereader";
    ConfigurationVO vo = FileReader.loadFile(path);
    System.out.println(vo);
  }
}
