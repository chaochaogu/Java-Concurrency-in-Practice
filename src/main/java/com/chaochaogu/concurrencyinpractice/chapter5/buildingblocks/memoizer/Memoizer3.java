package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 基于 FutureTask 的 Memoizing 封装器
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
        Future<V> future = cache.get(arg);
        if (Objects.isNull(future)) {
            FutureTask<V> futureTask = new FutureTask<>(() -> c.compute(arg));
            future = futureTask;
            cache.put(arg, futureTask);
            futureTask.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    /**
     * 强制将未检查的Throwable转换为RuntimeException
     *
     * @param t
     * @return
     */
    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof Exception) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("not unchecked " + t);
        }
    }
}
