package com.chaochaogu.concurrencyinpractice.chapter1.introduction;


import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 线程安全的数值序列生成器
 *
 * @author chaochao gu
 * @date 2019/12/2
 */
@ThreadSafe
public class Sequence {

    @GuardedBy("this")
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
