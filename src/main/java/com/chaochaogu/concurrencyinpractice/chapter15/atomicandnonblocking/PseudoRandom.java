package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import java.util.Random;

/**
 * @author chaochao Gu
 * @date 2020/4/24
 */
public class PseudoRandom {

    public int calculateNext(int i){
        return new Random().nextInt(i);
    }
}
