package com.github.xionghuicoder.testcase.configuration;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.xionghuicoder.configuration.ConfigurationVO;
import com.github.xionghuicoder.configuration.FileReader;
import com.github.xionghuicoder.core.Level;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.DEFAULT)
public class FileReaderTest extends TestCase {

  @Test
  public void testLoad() {
    String path = "filereader";
    ConfigurationVO vo = FileReader.loadFile(path);
    assert vo.getCount() == 10 && vo.getBufferSize() == 100 && vo.getMinThreshold() == Level.INFO;
  }
}
