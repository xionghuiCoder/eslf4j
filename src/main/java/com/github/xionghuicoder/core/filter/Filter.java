package com.github.xionghuicoder.core.filter;

import com.github.xionghuicoder.core.bean.MessageBean;

/**
 * 过滤器，用于处理messageBean <br>
 * 如果返回false，则表示{@link com.github.xionghuicoder.core.LogManager LogManager}不处理该messageBean
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Filter {

  boolean doFilter(MessageBean messageBean);
}
