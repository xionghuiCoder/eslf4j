package com.jd.o2o.core.queue;

import junit.framework.TestCase;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.jd.o2o.core.bean.MessageBean;

/**
 * 测试FixedQueue
 *
 * @author xionghui
 * @date 2015年6月20日
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixedQueueTest extends TestCase {
  public void test_offer() {
    System.out.println("test_offer begin");
    FixedQueue queue = new FixedQueue(2);
    queue.add(newBean("a"));
    queue.add(newBean("b"));
    queue.add(newBean("c"));
    System.out.println(queue);
    System.out.println("test_offer end");
  }

  public void test_poll() {
    System.out.println("test_poll begin");
    FixedQueue queue = new FixedQueue(2);
    queue.add(newBean("a"));
    queue.add(newBean("b"));
    queue.remove();
    System.out.println(queue);
    System.out.println("test_poll end");
  }

  private MessageBean newBean(String msg) {
    return new MessageBean.Builder(msg).throwable(new Throwable(msg)).build();
  }
}
