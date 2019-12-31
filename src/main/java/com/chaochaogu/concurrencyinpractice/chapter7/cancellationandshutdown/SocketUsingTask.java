package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 通过newTaskFor将非标准的取消操作封装在一个任务中
 *
 * @author chaochao Gu
 * @date 2019/12/31
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
    @GuardedBy("this")
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    @Override
    public void cancel() {
        try {
            if (Objects.nonNull(socket))
                socket.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
