package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

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
}
