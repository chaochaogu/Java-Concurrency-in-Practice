package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于ReentrantLock实现的随机数生成器
 *
 * @author chaochao Gu
 * @date 2020/4/24
 */
@ThreadSafe
public class ReentrantLockPseudoRandom extends PseudoRandom {
    private final Lock lock = new ReentrantLock();
    private int seed;

    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            seed = calculateNext(s);
            int remainder = s % n;
            return remainder > 0 ? remainder : remainder + n;
        } finally {
            lock.unlock();
        }
    }
}
