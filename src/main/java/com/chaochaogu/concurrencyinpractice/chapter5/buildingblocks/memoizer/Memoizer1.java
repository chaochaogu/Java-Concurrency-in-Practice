package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 使用HashMap和同步机制来初始化缓存
 *
 * @author chaochao Gu
 * @date 2019/12/24
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();

    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (Objects.isNull(result)) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
