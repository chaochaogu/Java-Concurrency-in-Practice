package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通过客户端加锁来实现“若没有则添加”
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
@ThreadSafe
public class GoodListHelper<E> {

    private List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }

}
