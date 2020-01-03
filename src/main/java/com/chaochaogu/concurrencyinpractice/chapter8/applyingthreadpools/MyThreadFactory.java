package com.chaochaogu.concurrencyinpractice.chapter8.applyingthreadpools;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义的线程工厂
 *
 * @author chaochao Gu
 * @date 2020/1/3
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
