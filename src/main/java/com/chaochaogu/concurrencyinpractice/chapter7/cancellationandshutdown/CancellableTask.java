package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author chaochao Gu
 * @date 2019/12/31
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}
