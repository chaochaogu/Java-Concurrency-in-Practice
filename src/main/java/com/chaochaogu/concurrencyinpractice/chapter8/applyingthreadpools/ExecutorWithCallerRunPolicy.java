package com.chaochaogu.concurrencyinpractice.chapter8.applyingthreadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 固定大小的线程池，采用有界队列以及调用者运行的饱和策略
 *
 * @author chaochao Gu
 * @date 2020/1/3
 */
public class ExecutorWithCallerRunPolicy {
    private final int CAPACITY = 1000;
    private final int CORE = Runtime.getRuntime().availableProcessors() * 2;
    private final int MAX = Runtime.getRuntime().availableProcessors() * 2;
    private final long KEEPALIVE = 60;

    public void callerRun() {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(CORE, MAX, KEEPALIVE, SECONDS,
                new LinkedBlockingQueue<>(CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 在调用构造函数后再定制ThreadPoolExecutor
        exec.setKeepAliveTime(30, SECONDS);
        // 无法再配置的exec
        ExecutorService unconfigurableExec = Executors.unconfigurableExecutorService(exec);
        // 转型以访问设置器
        ExecutorService cachedExec = Executors.newCachedThreadPool();
        if (cachedExec instanceof ThreadPoolExecutor)
            ((ThreadPoolExecutor) cachedExec).setCorePoolSize(10);
        else
            throw new AssertionError("Oops, bad assumption");
    }
}
