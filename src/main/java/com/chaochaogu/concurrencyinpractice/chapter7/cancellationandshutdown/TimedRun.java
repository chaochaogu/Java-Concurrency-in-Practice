package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.concurrent.*;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

/**
 * 通过Future来取消任务
 *
 * @author chaochao Gu
 * @date 2019/12/31
 */
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            // 如果在任务中抛出了异常，那么重新抛出该异常
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            // 接下来任务将被取消
        } finally {
            // 如果任务已经结束，那么执行取消操作也不会有任何影响
            // 如果任务正在运行，那么将被中断
            task.cancel(true);
        }
    }
}
