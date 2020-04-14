package com.chaochaogu.concurrencyinpractice.chapter14.buildingcustomsynchronizers;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 有界缓存实现的基类
 *
 * @author chaochao Gu
 * @date 2020/4/14
 */
@ThreadSafe
public abstract class BaseBoundedBuffer<V> {
    @GuardedBy("this") private final V[] buf;
    @GuardedBy("this") private int head;
    @GuardedBy("this") private int tail;
    @GuardedBy("this") private int count;

    protected BaseBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        count++;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        --count;
        return v;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
