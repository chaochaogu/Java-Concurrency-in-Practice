package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作中（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/30
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        cancelled = true;
    }
}
