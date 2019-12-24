package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 *
 * @author chaochao Gu
 * @date 2019/12/24
 */
public class BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean add = false;
        try {
            add = set.add(o);
            return add;
        } finally {
            if (!add) {
                semaphore.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean remove = set.remove(o);
        if (remove) {
            semaphore.release();
        }
        return remove;
    }

}
