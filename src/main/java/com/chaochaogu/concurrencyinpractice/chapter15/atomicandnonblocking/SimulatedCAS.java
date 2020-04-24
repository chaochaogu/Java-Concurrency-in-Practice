package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 模拟CAS操作
 *
 * @author chaochao Gu
 * @date 2020/4/23
 */
@ThreadSafe
public class SimulatedCAS {
    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
