package com.jd.o2o.core.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.jd.o2o.core.bean.MessageBean;

/**
 * 固定长度的队列<br />
 * FixedQueue是非线程安全的，但它总被threadLocal包装
 *
 * @author xionghui
 * @date 2015年6月16日
 *
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
    if (itemList.size() == capacity) {
      poll();
    }
    itemList.add(e);
    String msg = e.getMessage();
    if (msg != null) {
      int len = msg.length();
      if (len != 0) {
        CHAR_COUNT.addAndGet(len);
        charLocal += len;
      }
    }
    return true;
  }

  @Override
  public MessageBean poll() {
    MessageBean bean = itemList.remove(0);
    if (bean != null) {
      String msg = bean.getMessage();
      if (msg != null) {
        int len = msg.length();
        if (len != 0) {
          CHAR_COUNT.addAndGet(-len);
          charLocal -= len;
        }
      }
    }
    return bean;
  }

  @Override
  public MessageBean peek() {
    throw new UnsupportedOperationException();
  }

  public void clear() {
    itemList = new LinkedList<MessageBean>();
    CHAR_COUNT.addAndGet(-charLocal);
    charLocal = 0;
  }


  @Override
  public Iterator<MessageBean> iterator() {
    return itemList.iterator();
  }

  @Override
  public int size() {
    return itemList.size();
  }

  public long getCharLocal() {
    return charLocal;
  }

  public static long getCharTotal() {
    return CHAR_COUNT.get();
  }
}
