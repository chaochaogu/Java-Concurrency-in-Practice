package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;

/**
 * 通过一个私有锁来保护状态
 *
 * @author chaochao Gu
 * @date 2019/12/12
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("this")
    private Widget widget;

    public void doSomething() {
        synchronized (myLock) {
            // 修改Widget的状态
        }
    }

    class Widget {
    }
}
