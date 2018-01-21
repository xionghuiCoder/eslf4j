package com.github.xionghuicoder.testcase.core.queue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.xionghuicoder.core.bean.MessageBean;
import com.github.xionghuicoder.core.queue.FixedQueue;

import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixedQueueTest extends TestCase {

  @Test
  public void testOffer() {
    System.out.println("test_offer begin");
    FixedQueue queue = new FixedQueue(2);
    queue.add(this.newBean("a"));
    queue.add(this.newBean("b"));
    queue.add(this.newBean("c"));
    System.out.println(queue);
    System.out.println("test_offer end");
    assert queue.size() == 2;
  }

  @Test
  public void testPoll() {
    System.out.println("test_poll begin");
    FixedQueue queue = new FixedQueue(2);
    queue.add(this.newBean("a"));
    queue.add(this.newBean("b"));
    queue.remove();
    System.out.println(queue);
    System.out.println("test_poll end");
    assert queue.size() == 1;
  }

  private MessageBean newBean(String msg) {
    return new MessageBean.Builder(msg).throwable(new Throwable(msg)).build();
  }
}
