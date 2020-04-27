package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 线程安全的延迟初始化
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@ThreadSafe
public class SafeLazyInitialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
        if (resource == null)
            resource = new Resource();
        return resource;
    }

    static class Resource {
    }
}
