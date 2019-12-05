package com.chaochaogu.concurrencyinpractice.chapter3;


import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 线程安全的可变整数类
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }

}
