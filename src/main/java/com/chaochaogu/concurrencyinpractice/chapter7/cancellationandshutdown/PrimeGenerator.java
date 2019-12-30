package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;
import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 使用volatile类型的域来保存取消状态
 *
 * @author chaochao Gu
 * @date 2019/12/30
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {

    private static Executor exec = Executors.newCachedThreadPool();
    private final List<BigInteger> primes = Lists.newArrayList();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return Lists.newArrayList(primes);
    }

    /**
     * 一个仅运行一秒钟的素数生成器
     *
     * @return
     * @throws InterruptedException
     */
    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
