package com.chaochaogu.concurrencyinpractice.chapter11.performanceandscalability;

import java.util.concurrent.BlockingQueue;

/**
 * 对任务队列的串行访问
 *
 * @author chaochao Gu
 * @date 2020/1/10
 */
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break; /* 允许线程退出 */
            }
        }
    }
}
