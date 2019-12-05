package com.chaochaogu.concurrencyinpractice.chapter1;


import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 非线程安全的数值序列生成器
 *
 * @author chaochao gu
 * @date 2019/12/2
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    public int getNext() {
        return value++;
    }
}
