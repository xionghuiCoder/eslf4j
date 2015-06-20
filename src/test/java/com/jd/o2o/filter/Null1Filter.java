package com.jd.o2o.filter;

import com.jd.o2o.core.bean.MessageBean;
import com.jd.o2o.core.filter.Filter;

/**
 * 空filter，用于测试加载配置文件
 *
 * @author xionghui
 * @date 2015年6月15日
 *
 */
public class Null1Filter implements Filter {

  @Override
  public boolean doFilter(MessageBean messageBean) {
    // do nothing
    return true;
  }
}
