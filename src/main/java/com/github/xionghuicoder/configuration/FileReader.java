package com.github.xionghuicoder.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.xionghuicoder.Eslf4jException;
import com.github.xionghuicoder.core.Level;
import com.github.xionghuicoder.core.filter.Filter;
import com.github.xionghuicoder.core.memory.IMemoryManager;
import com.github.xionghuicoder.helpers.LoaderHelper;
import com.github.xionghuicoder.helpers.LogHelper;

/**
 * 该类主要用于解析配置文件，具体解析规则{@link #resolveFile resolveFile}；不用Properties是因为properties不支持配置多个filter
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileReader {
  // 编码字符集
  private static final String CHARSET_NAME = "utf-8";
  // 文件的key、value分割符号
  private static final char SPLIT = '=';
  // 单个斜杠分隔符
  private static final char SINGLE_NOTE = '/';
  // 注释：//...注释一行，多行注释请使用多个//...
  private static final String NOTE = "//";

  public static ConfigurationVO loadFile(String path) {
    ConfigurationVO cfgVO = null;
    URL url = LoaderHelper.getResource(path);
    if (url != null) {
      LogHelper.debug("Using URL [" + url + "] for eslf4j configuration.");
      cfgVO = loadConfigure(url);
    } else {
      LogHelper.debug("Could not find resource: [" + path + "].");
    }
    return cfgVO;
  }

  private static ConfigurationVO loadConfigure(URL configURL) {
    LogHelper.debug("Reading configuration from URL " + configURL);
    ConfigurationVO cfgVO = null;
    InputStream inStream = null;
    InputStreamReader inputReader = null;
    BufferedReader reader = null;
    try {
      URLConnection uConn = configURL.openConnection();
      uConn.setUseCaches(false);
      inStream = uConn.getInputStream();
      inputReader = new InputStreamReader(inStream, CHARSET_NAME);
      reader = new BufferedReader(inputReader);
      cfgVO = resolveFile(reader);
    } catch (IOException e) {
      LogHelper.error("Could not read configuration file from URL [" + configURL + "].", e);
    } catch (Eslf4jException e) {
      throw e;
    } catch (Exception e) {
      throw new Eslf4jException(e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      } finally {
        try {
          if (inputReader != null) {
            inputReader.close();
          }
        } catch (IOException e) {
        } finally {
          if (inStream != null) {
            try {
              inStream.close();
            } catch (IOException e) {
            }
          }
        }
      }
    }
    return cfgVO;
  }

  /**
   * 处理过程：按行读取字符串，去掉注释部分，把////转换成//，按{@link #SPLIT SPLIT}分割字符串，去掉编码key前后的空字符。
   *
   * <p>
   * 1、解析时会把//后面的内容当做注释忽略掉，如果需要使用//则要用////代替（不支持/*...* /注释）；<br>
   * 2、解析当前行若没有发现=，会忽略当前行；<br>
   * 3、解析时会处理编码key，把它前后的（\t:tab制表符、\r:回车、\n:换行、\f:走纸换页、\b:退格和空格符号 ）去掉；但不会处理编码对应的值value。<br>
   * </p>
   *
   * @param reader
   * @return
   * @throws IOException
   */
  private static ConfigurationVO resolveFile(BufferedReader reader) throws IOException {
    ConfigurationVO cfgVO = new ConfigurationVO();
    while (true) {
      String line = reader.readLine();
      // 此时到达行末尾
      if (line == null) {
        break;
      }
      int len = line.length();
      StringBuilder keyBuilder = new StringBuilder(len);
      StringBuilder valueBuilder = new StringBuilder(len);
      StringBuilder noteBuilder = new StringBuilder(4);
      // 是否没有等号
      boolean noneEqual = true;
      for (int i = 0; i < len; i++) {
        if (line.charAt(i) == '/') {
          noteBuilder.append(line.charAt(i));
          if ((NOTE + NOTE).equals(noteBuilder.toString())) {
            /** 把////转换为// */
            if (noneEqual) {
              keyBuilder.append(NOTE);
            } else {
              valueBuilder.append(NOTE);
            }
            noteBuilder.setLength(0);
          }
          continue;
        }
        if (noteBuilder.length() > 0) {
          if (noteBuilder.length() == 1) {
            if (noneEqual) {
              keyBuilder.append(SINGLE_NOTE);
            } else {
              valueBuilder.append(SINGLE_NOTE);
            }
            noteBuilder.setLength(0);
          } else {
            break;
          }
        }
        if (noneEqual) {
          if (line.charAt(i) == SPLIT) {
            noneEqual = false;
            continue;
          }
          keyBuilder.append(line.charAt(i));
        } else {
          valueBuilder.append(line.charAt(i));
        }
      }
      if (noneEqual) {
        // 若不包括SPLIT，则忽略该行
        continue;
      }
      if (noteBuilder.length() == 1) {
        valueBuilder.append(SINGLE_NOTE);
      }
      // 去掉编码前后的（\t:tab制表符、\r:回车、\n:换行、\f:走纸换页、\b:退格和空格符号）
      String key = keyBuilder.toString().trim();
      // 不处理编码对应值的（\t:tab制表符、\r:回车、\n:换行、\f:走纸换页、\b:退格和空格符号）
      String value = valueBuilder.toString();
      // 填充vo
      ConfigurationHelper.fillVO(cfgVO, key, value);
    }
    return cfgVO;
  }

  private static class ConfigurationHelper {

    public static void fillVO(ConfigurationVO cfgVO, String key, String value) {
      if ("count".equals(key)) {
        setCount(cfgVO, value);
      } else if ("buffersize".equals(key)) {
        setBufferSize(cfgVO, value);
      } else if ("minthreshold".equals(key)) {
        setMinThreshold(cfgVO, value);
      } else if ("filter".equals(key)) {
        setFilter(cfgVO, value);
      } else if ("memorymanager".equals(key)) {
        setMemoryManager(cfgVO, value);
      } else {
        throw new Eslf4jException("invalid configuration params.");
      }
    }

    private static void setCount(ConfigurationVO cfgVO, String value) {
      int count = Integer.valueOf(value);
      cfgVO.setCount(count);
    }

    private static void setBufferSize(ConfigurationVO cfgVO, String value) {
      long charSize = 0;
      String size = value.substring(0, value.length() - 1);
      String lastChar = value.substring(value.length() - 1);
      if ("b".equalsIgnoreCase(lastChar)) {
        charSize = Integer.valueOf(size) / 2;
      } else if ("c".equalsIgnoreCase(lastChar)) {
        charSize = Integer.valueOf(size);
      } else if ("k".equalsIgnoreCase(lastChar)) {
        charSize = Integer.valueOf(size) * 1024 / 2;
      } else if ("m".equalsIgnoreCase(lastChar)) {
        charSize = Integer.valueOf(size) * 1024 * 1024 / 2;
      } else if ("g".equalsIgnoreCase(lastChar)) {
        charSize = Integer.valueOf(size) * 1024 * 1024 * 1024 / 2;
      } else {
        charSize = Integer.valueOf(value);
      }
      cfgVO.setBufferSize(charSize);
    }

    private static void setMinThreshold(ConfigurationVO cfgVO, String value) {
      Level minThreshold = Level.getLevelByValue(value);
      cfgVO.setMinThreshold(minThreshold);
    }

    private static void setFilter(ConfigurationVO cfgVO, String value) {
      Set<Filter> filterSet = cfgVO.getFilterSet();
      if (filterSet == null) {
        filterSet = new LinkedHashSet<Filter>();
      }
      Filter filter = null;
      try {
        Class<?> filterClass = Class.forName(value);
        filter = (Filter) filterClass.newInstance();
      } catch (Exception e) {
        throw new Eslf4jException(e);
      }
      filterSet.add(filter);
      cfgVO.setFilterSet(filterSet);
    }

    private static void setMemoryManager(ConfigurationVO cfgVO, String value) {
      IMemoryManager memoryManager = null;
      try {
        Class<?> clazz = Class.forName(value);
        memoryManager = (IMemoryManager) clazz.newInstance();
      } catch (Exception e) {
        throw new Eslf4jException(e);
      }
      cfgVO.setMemoryManager(memoryManager);
    }
  }
}
