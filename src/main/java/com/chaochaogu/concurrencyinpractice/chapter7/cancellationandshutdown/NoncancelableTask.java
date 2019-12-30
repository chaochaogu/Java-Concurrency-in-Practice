package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.concurrent.BlockingQueue;

/**
 * 不可取消的任务在退出前恢复中断状态
 *
 * @author chaochao Gu
 * @date 2019/12/30
 */
public class NoncancelableTask {

    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // 重新尝试
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    interface Task {
    }
}
