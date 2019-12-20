package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.Vector;

/**
 * @author chaochao Gu
 * @date 2019/12/20
 */
public class ConcurrentModification {

    private Vector<Integer> vector = new Vector();

    public void test(){
        // 可能抛出 ArrayIndexOutOfBoundsException 的迭代操作
        for (int i = 0; i < vector.size(); i++) {
            doSomething(vector.get(i));
        }
        // 带有客户端加锁的迭代
        synchronized (vector){
            for (int i = 0; i < vector.size(); i++) {
                doSomething(vector.get(i));
            }
        }
    }

    private void doSomething(Integer integer) {
    }

}
