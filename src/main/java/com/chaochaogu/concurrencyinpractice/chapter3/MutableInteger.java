package com.chaochaogu.concurrencyinpractice.chapter3;


import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 非线程安全的可变整数类
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
