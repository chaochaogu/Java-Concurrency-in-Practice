package com.chaochaogu.concurrencyinpractice.chapter2.threadsafety;

/**
 * 如果内置锁不是可重入的，那么下面这段代码将发生死锁
 *
 * @author chaochao gu
 * @date 2019/12/4
 */
public class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething() {
        super.doSomething();
    }
}

class Widget {
    public synchronized void doSomething() {
    }
}
