package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.Vector;

/**
 * 在使用客户端加锁的Vector上的复合操作
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
public class SafeVectorHelpers {

    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

}
