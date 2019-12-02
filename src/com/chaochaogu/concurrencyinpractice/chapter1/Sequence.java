package com.chaochaogu.concurrencyinpractice.chapter1;

import com.chaochaogu.concurrencyinpractice.model.GuardeBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 线程安全的数值序列生成器
 *
 * @author chaochao gu
 * @date 2019/12/2
 */
@ThreadSafe
public class Sequence {

    @GuardeBy("this")
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
