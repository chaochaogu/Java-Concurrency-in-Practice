package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.Vector;

/**
 * Vector上可能导致混乱结果的复合操作
 *
 * @author chaochao Gu
 * @date 2019/12/20
 */
public class UnsafeVectorHelpers {

    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }

}
