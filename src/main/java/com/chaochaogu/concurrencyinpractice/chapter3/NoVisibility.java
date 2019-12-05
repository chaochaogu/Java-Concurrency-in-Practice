package com.chaochaogu.concurrencyinpractice.chapter3;


import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 在没有同步的情况下共享变量（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
@NotThreadSafe
public class NoVisibility {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) {

        new Thread(() -> {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }).start();
        number = 42;
        ready = true;

    }
}
