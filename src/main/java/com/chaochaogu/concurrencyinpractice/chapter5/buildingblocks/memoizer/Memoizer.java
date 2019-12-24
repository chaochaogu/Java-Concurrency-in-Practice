package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Memoizer的最终实现
 *
 * @author chaochao gu
 * @date 2019/12/25
 */
public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (Objects.isNull(f)) {
                FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
                f = cache.putIfAbsent(arg, ft);
                if (Objects.isNull(f)) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }

    /**
     * 强制将未检查的Throwable转换为RuntimeException
     *
     * @param t
     * @return
     */
    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("not unchecked " + t);
        }
    }
}
