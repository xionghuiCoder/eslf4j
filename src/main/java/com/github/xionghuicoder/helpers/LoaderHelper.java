package com.github.xionghuicoder.helpers;

import java.net.URL;

/**
 * 资源加载
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoaderHelper {

  public static URL getResource(String resource) {
    ClassLoader classLoader = LoaderHelper.class.getClassLoader();
    LogHelper.debug("Trying to find [" + resource + "] using " + classLoader + " class loader.");
    URL url = classLoader.getResource(resource);
    if (url != null) {
      return url;
    }
    LogHelper.debug("Trying to find [" + resource + "] using ClassLoader.getSystemResource().");
    return ClassLoader.getSystemResource(resource);
  }
}
