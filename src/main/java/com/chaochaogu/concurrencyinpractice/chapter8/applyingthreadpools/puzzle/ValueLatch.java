package com.chaochaogu.concurrencyinpractice.chapter8.applyingthreadpools.puzzle;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * 由ConcurrentPuzzleSolver使用的携带结果的闭锁
 *
 * @author chaochao gu
 * @date 2020/1/3
 */
@ThreadSafe
public class ValueLatch<T> {
    @GuardedBy("this")
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return (done.getCount() == 0);
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
