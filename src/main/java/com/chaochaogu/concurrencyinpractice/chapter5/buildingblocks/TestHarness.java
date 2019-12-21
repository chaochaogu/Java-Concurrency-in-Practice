package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 在计时测试中使用CountDownLatch来启动和停止线程
 *
 * @author chaochao gu
 * @date 2019/12/22
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            }).start();
        }

        Stopwatch stopwatch = Stopwatch.createStarted();
        startGate.countDown();
        endGate.await();
        stopwatch.stop();
        return stopwatch.elapsed(TimeUnit.SECONDS);
    }

}
