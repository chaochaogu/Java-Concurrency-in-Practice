package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 延长初始化占位类模式
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getInstance() {
        return ResourceHolder.resource;
    }

    static class Resource {
    }
}
