package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 支持关闭操作的Web服务器
 *
 * @author chaochao Gu
 * @date 2019/12/26
 */
@Slf4j
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newCachedThreadPool();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                Runnable task = () -> handleRequest(conn);
                exec.execute(task);
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    log.error("task submission rejected", e);
                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    private void handleRequest(Socket conn) {
        Request req = readRequest(conn);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    private void dispatchRequest(Request req) {
    }

    private boolean isShutdownRequest(Request req) {
        return false;
    }

    private Request readRequest(Socket conn) {
        return null;
    }

    interface Request {
    }

}
