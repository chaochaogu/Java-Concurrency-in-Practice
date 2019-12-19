package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * NumberRange类并不足以保护它的不变性条件（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/19
 */
public class NumberRange {
    // 不变性条件：lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);

    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // 注意 -- 不安全的先检查后执行
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to :" + i + "> upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // 注意 -- 不安全的先检查后执行
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to :" + i + "< lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }

}
