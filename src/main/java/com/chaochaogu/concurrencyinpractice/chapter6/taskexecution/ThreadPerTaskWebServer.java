package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在Web服务器中为每个请求启动一个新的线程（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/25
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            new Thread(() -> handleRequest(connection)).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
