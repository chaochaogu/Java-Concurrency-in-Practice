package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏在字符串连接中的迭代操作（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
public class HiddenIterator {

    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            set.add(r.nextInt());
        }
        System.out.println("DEBUG : add ten elements to :" + set);
    }

}
