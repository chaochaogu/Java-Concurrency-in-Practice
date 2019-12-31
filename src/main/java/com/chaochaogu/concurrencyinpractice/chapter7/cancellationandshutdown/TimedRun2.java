package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;
import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * 在专门的线程中中断任务
 *
 * @author chaochao Gu
 * @date 2019/12/31
 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = newScheduledThreadPool(1);

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (Objects.nonNull(t)) {
                    throw launderThrowable(t);
                }
            }
        }
        RethrowableTask task = new RethrowableTask();
        Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

}
