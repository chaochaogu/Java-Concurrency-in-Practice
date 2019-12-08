package com.chaochaogu.concurrencyinpractice.chapter2.thread.safety;


import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 该Servlet在没有足够原子性保证的情况下对其最近计算结果进行缓存（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/4
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        if (Objects.equals(i, lastNumber.get())) {
            encodeIntoResponse(response, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(response, factors);
        }


    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] bigIntegers) {
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return null;
    }
}
