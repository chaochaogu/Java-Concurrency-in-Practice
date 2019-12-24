package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer;

import java.math.BigInteger;

/**
 * @author chaochao Gu
 * @date 2019/12/24
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        // 在经过长时间的计算后
        return new BigInteger(arg);
    }
}
