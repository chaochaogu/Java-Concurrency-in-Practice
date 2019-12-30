package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来取消
 *
 * @author chaochao Gu
 * @date 2019/12/30
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* 允许线程退出 */
        }
    }

    public void cancel() {
        interrupt();
    }
}
