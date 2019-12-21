package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.concurrent.BlockingQueue;

/**
 * 恢复中断状态以避免屏蔽中断
 *
 * @author chaochao gu
 * @date 2019/12/22
 */
public class TaskRunnable implements Runnable {

    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // 恢复被中断的状态
            Thread.currentThread().interrupt();
        }

    }

    private void processTask(Task task) {
        // handle the task
    }

    interface Task {
    }
}
