package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的Web服务器
 *
 * @author chaochao Gu
 * @date 2019/12/25
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            EXECUTOR.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
