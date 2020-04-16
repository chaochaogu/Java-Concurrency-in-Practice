package com.chaochaogu.concurrencyinpractice.chapter14.buildingcustomsynchronizers;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 使用条件队列实现的有界缓存
 *
 * @author chaochao Gu
 * @date 2020/4/16
 */
@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    // CONDITION PREDICATE : not-full(!isFull())
    // CONDITION PREDICATE : not-empty(!isEmpty())
    int SLEEP_GRANULARITY = 60;

    public BoundedBuffer() {
        this(100);
    }

    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    // BLOCKS-UNTIL:not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull())
            wait();
        doPut(v);
        notifyAll();
    }

    // BLOCKS-UNTIL:not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty())
            wait();
        V v = doTake();
        notifyAll();
        return v;
    }

    // BLOCKS-UNTIL:not-full
    // Alternate form of put() using conditional notification
    public synchronized void alternatePut(V v) throws InterruptedException {
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty)
            notifyAll();
    }
}
