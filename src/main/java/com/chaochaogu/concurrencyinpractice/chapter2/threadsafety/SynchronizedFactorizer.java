package com.chaochaogu.concurrencyinpractice.chapter2.threadsafety;


import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 这个Servlet能正确地缓存最新的计算结果，但并发性却非常糟糕（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/4
 */
@ThreadSafe
public class SynchronizedFactorizer extends GenericServlet implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        if (Objects.equals(i, lastNumber)) {
            encodeIntoResponse(response, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(response, factors);
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] lastFactors) {
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return null;
    }
}
