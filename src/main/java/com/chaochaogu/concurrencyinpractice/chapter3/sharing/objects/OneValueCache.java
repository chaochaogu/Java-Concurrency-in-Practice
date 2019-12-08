package com.chaochaogu.concurrencyinpractice.chapter3.sharing.objects;

import com.chaochaogu.concurrencyinpractice.model.Immutable;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * 对数值及其因数分解结果进行缓存的不可变容器类
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
@Immutable
public class OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        //TODO 如果此处没有调用Arrays.copyOf，那么oneValueCache就不是不可变的
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (Objects.isNull(lastNumber) || !Objects.equals(i, lastNumber)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
