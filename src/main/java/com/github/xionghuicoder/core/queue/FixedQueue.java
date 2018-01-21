package com.github.xionghuicoder.core.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.github.xionghuicoder.core.bean.MessageBean;

/**
 * 固定长度的队列<br>
 *
 * FixedQueue是非线程安全的；不过没关系，因为它总被threadLocal使用
 *
 * @author xionghui
 * @version 1.0.0
 * @since 1.0.0
 */
public class FixedQueue extends AbstractQueue<MessageBean> {
  private static final AtomicLong CHAR_COUNT = new AtomicLong();

  private long charLocal;

  private List<MessageBean> itemList = new LinkedList<MessageBean>();

  private final int capacity;

  public FixedQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.capacity = capacity;
  }

  @Override
  public boolean offer(MessageBean e) {
    if (e == null) {
      throw new NullPointerException();
    }
    if (this.itemList.size() == this.capacity) {
      this.poll();
    }
    this.itemList.add(e);
    String msg = e.getMessage();
    if (msg != null) {
      int len = msg.length();
      if (len != 0) {
        CHAR_COUNT.addAndGet(len);
        this.charLocal += len;
      }
    }
    return true;
  }

  @Override
  public MessageBean poll() {
    MessageBean bean = this.itemList.remove(0);
    if (bean != null) {
      String msg = bean.getMessage();
      if (msg != null) {
        int len = msg.length();
        if (len != 0) {
          CHAR_COUNT.addAndGet(-len);
          this.charLocal -= len;
        }
      }
    }
    return bean;
  }

  @Override
  public MessageBean peek() {
    // 不支持，因为获取必消费
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    this.itemList = new LinkedList<MessageBean>();
    CHAR_COUNT.addAndGet(-this.charLocal);
    this.charLocal = 0;
  }


  @Override
  public Iterator<MessageBean> iterator() {
    return this.itemList.iterator();
  }

  @Override
  public int size() {
    return this.itemList.size();
  }

  public long getCharLocal() {
    return this.charLocal;
  }

  public static long getCharTotal() {
    return CHAR_COUNT.get();
  }
}
