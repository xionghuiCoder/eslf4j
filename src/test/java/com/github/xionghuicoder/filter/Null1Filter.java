package com.github.xionghuicoder.filter;

import com.github.xionghuicoder.core.bean.MessageBean;
import com.github.xionghuicoder.core.filter.Filter;

public class Null1Filter implements Filter {

  @Override
  public boolean doFilter(MessageBean messageBean) {
    // do nothing
    return true;
  }
}
