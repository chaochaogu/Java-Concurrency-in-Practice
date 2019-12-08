package com.chaochaogu.concurrencyinpractice.chapter2.threadsafety;


import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 延迟初始化中的竞态条件（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/3
 */
@NotThreadSafe
public class LazyInitRace {

    private Object instance = null;

    public Object getInstance() {
        if (instance == null) {
            instance = new Object();
        }
        return instance;
    }
}
