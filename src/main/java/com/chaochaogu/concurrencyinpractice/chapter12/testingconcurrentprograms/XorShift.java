package com.chaochaogu.concurrencyinpractice.chapter12.testingconcurrentprograms;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 适合在测试中使用的随机数生成器
 *
 * @author chaochao Gu
 * @date 2020/1/19
 */
public class XorShift {
    static final AtomicInteger seq = new AtomicInteger(8862213);
    int x = -1831433054;

    public XorShift(int seed) {
        x = seed;
    }

    public XorShift() {
        this((int) System.nanoTime() + seq.getAndAdd(129));
    }

    public int next() {
        x ^= x << 6;
        x ^= x >>> 21;
        x ^= (x << 7);
        return x;
    }
}
