package com.jd.o2o;

import junit.framework.TestCase;

import com.jd.o2o.core.Slf4jLoggerAdapter;

/**
 * 反编译查看
 *
 * @author xionghui
 * @date 2015年6月17日
 *
 */
public class StringPlusTest extends TestCase {
  private final static String TAG = Slf4jLoggerAdapter.class.getName();

  public void test_plus() {
    String test = "[" + TAG + ":" + "trace" + "]";
    System.out.println("see the .class with " + test);
  }
}
