package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于AtomicInteger实现的随机数生成器
 *
 * @author chaochao Gu
 * @date 2020/4/24
 */
@ThreadSafe
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;

    public AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = calculateNext(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}
