package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.util.concurrent.Executor;

/**
 * 在调用线程中以同步方式执行所有任务的Executor
 *
 * @author chaochao Gu
 * @date 2019/12/25
 */
public class WithinThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
