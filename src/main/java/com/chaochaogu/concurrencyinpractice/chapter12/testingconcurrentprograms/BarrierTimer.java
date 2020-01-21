package com.chaochaogu.concurrencyinpractice.chapter12.testingconcurrentprograms;

/**
 * 基于栅栏的定时器
 *
 * @author chaochao Gu
 * @date 2020/1/21
 */
public class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}
