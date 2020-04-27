package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 提前初始化
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@ThreadSafe
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getInstance() {
        return resource;
    }

    static class Resource {
    }
}
