package com.chaochaogu.concurrencyinpractice.chapter10.avoidinglivenesshazards;

/**
 * 简单的锁顺序死锁（不要这么做）
 *
 * @author chaochao Gu
 * @date 2020/1/6
 */
public class LeftRightDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    private void doSomething() {
    }

    private void doSomethingElse() {
    }
}
