package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 双重检查加锁（不要这样做）
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@NotThreadSafe
public class DoubleCheckedLocking {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }

    static class Resource {
    }
}
