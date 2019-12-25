package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.util.concurrent.Executor;

/**
 * 为每个请求启动一个新线程的Executor
 *
 * @author chaochao Gu
 * @date 2019/12/25
 */
public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
