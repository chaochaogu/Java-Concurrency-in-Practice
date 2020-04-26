package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

/**
 * 如果在程序中没有包含足够的同步，那么可能产生奇怪的结果（不要这么做）
 *
 * @author chaochao Gu
 * @date 2020/4/26
 */
public class PossibleReordering {
    static int a = 0, b = 0;
    static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });

        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("( x:" + x + ", y:" + y + " )");
    }
}
