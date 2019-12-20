package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 非线程安全的“若没有则添加”（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
@NotThreadSafe
public class BadListHelper<E> {

    private List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }

}
