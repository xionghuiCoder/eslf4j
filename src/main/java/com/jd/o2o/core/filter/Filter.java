package com.jd.o2o.core.filter;

import com.jd.o2o.core.bean.MessageBean;

/**
 * 过滤器，用于处理messageBean<br />
 * 如果返回false，则表示{@LogManager}不处理该messageBean
 *
 * @author xionghui
 * @date 2015年6月13日
 *
 */
public interface Filter {
  public boolean doFilter(MessageBean messageBean);
}
