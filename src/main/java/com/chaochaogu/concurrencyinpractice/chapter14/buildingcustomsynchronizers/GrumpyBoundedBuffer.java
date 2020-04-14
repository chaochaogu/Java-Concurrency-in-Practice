package com.chaochaogu.concurrencyinpractice.chapter14.buildingcustomsynchronizers;

import com.chaochaogu.concurrencyinpractice.model.BufferEmptyException;
import com.chaochaogu.concurrencyinpractice.model.BufferFullException;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 当不满足前提条件时，有界缓存不会执行相应的操作
 *
 * @author chaochao Gu
 * @date 2020/4/14
 */
@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer() {
        this(100);
    }

    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }

    class ExampleUsage {
        private GrumpyBoundedBuffer<String> buffer;
        int SLEEP_GRANULARITY = 50;

        void useBuffer() throws InterruptedException {
            while (true) {
                try {
                    String item = buffer.take();
                    // use item
                    break;
                } catch (BufferEmptyException e) {
                    Thread.sleep(SLEEP_GRANULARITY);
                }
            }
        }
    }
}
