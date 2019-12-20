package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.Vector;

/**
 * 扩展Vector并增加一个“若没有则添加”方法
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    public boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }

}
