package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

/**
 * @author chaochao Gu
 * @date 2019/12/24
 */
public interface Computable<A, V> {
    V compute(A args) throws InterruptedException;
}
