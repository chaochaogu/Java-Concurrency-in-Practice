package com.chaochaogu.concurrencyinpractice.chapter4.composing.objects;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.Objects;

/**
 * 使用Java监视器模式的线程安全计数器
 *
 * @author chaochao gu
 * @date 2019/12/9
 */
@ThreadSafe
public final class Counter {

    @GuardedBy("this")
    private long value;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (Objects.equals(value, Long.MAX_VALUE)) {
            throw new IllegalStateException("counter overflow.");
        }
        return ++value;
    }
}
