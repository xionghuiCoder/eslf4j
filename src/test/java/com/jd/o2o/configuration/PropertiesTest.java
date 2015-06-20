package com.jd.o2o.configuration;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import junit.framework.TestCase;

import com.jd.o2o.helpers.LoaderHelper;

/**
 * 测试properties load是否会覆盖相同的key，因为配置文件可能配置多个filter。
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class PropertiesTest extends TestCase {
  public void test_samekey() {
    System.out.println("test same key:");
    String path = "same_key.properties";
    Properties props = loadConfigure(path);
    System.out.println(props);
  }

  private Properties loadConfigure(String path) {
    URL url = LoaderHelper.getResource(path);
    if (url == null) {
      throw new RuntimeException("url is null");
    }
    Properties props = new Properties();
    InputStream istream = null;
    URLConnection uConn = null;
    try {
      uConn = url.openConnection();
      uConn.setUseCaches(false);
      istream = uConn.getInputStream();
      props.load(istream);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (istream != null) {
        try {
          istream.close();
        } catch (InterruptedIOException ignore) {
          Thread.currentThread().interrupt();
        } catch (Exception e) {
        }
      }
    }
    return props;
  }

  public void test_annotation() {
    System.out.println("test annotation:");
    String path = "annotation.properties";
    Properties props = loadConfigure(path);
    System.out.println(props);
  }
}
