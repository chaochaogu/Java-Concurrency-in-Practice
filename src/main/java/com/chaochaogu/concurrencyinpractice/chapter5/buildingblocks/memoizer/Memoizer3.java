package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

/**
 * 基于FutureTask的Memoizing封装器
 *
 * @author chaochao Gu
 * @date 2019/12/24
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (Objects.isNull(f)) {
            FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }
}
