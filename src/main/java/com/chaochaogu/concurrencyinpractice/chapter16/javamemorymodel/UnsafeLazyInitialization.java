package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;


/**
 * 不安全的延迟初始化（不要这么做）
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@NotThreadSafe
public class UnsafeLazyInitialization {
    private static Resource resource;

    public Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }

    static class Resource {
    }
}
