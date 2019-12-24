package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用ConcurrentHashMap替换HashMap
 *
 * @author chaochao Gu
 * @date 2019/12/24
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
