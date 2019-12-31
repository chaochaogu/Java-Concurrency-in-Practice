package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 在外部线程中安排中断（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/31
 */
public class TimedRun1 {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        Thread taskThread = Thread.currentThread();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        r.run();
    }

}
