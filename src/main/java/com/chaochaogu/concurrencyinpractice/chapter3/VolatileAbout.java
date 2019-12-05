package com.chaochaogu.concurrencyinpractice.chapter3;

/**
 * 数绵羊
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
public class VolatileAbout {

    volatile boolean asleep;

    public void count(String[] args) {
        while (!asleep) {
            countSomeSheep();
        }
    }

    private void countSomeSheep() {
    }
}
