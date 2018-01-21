package com.github.xionghuicoder.testcase;

import org.junit.Test;

import com.github.xionghuicoder.core.Slf4jLoggerAdapter;

import junit.framework.TestCase;

public class StringPlusTest extends TestCase {
  private final static String TAG = Slf4jLoggerAdapter.class.getName();

  @Test
  public void testPlus() {
    String test = "[" + TAG + ":" + "trace" + "]";
    System.out.println("see the .class with " + test);
  }
}
