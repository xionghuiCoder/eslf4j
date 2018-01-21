package com.github.xionghuicoder.testcase.configuration;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.xionghuicoder.helpers.LoaderHelper;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.DEFAULT)
public class PropertiesTest extends TestCase {

  @Test
  public void testSamekey() {
    System.out.println("test same key:");
    String path = "same_key.properties";
    Properties props = this.loadConfigure(path);
    System.out.println(props);
    assert props.size() == 1;
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

  @Test
  public void testAnnotation() {
    System.out.println("test annotation:");
    String path = "annotation.properties";
    Properties props = this.loadConfigure(path);
    System.out.println(props);
  }
}
