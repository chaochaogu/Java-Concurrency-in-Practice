package com.chaochaogu.concurrencyinpractice.chapter2.threadsafety;


import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 缓存最近执行因数分解的数值及其计算结果的Servlet
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {

    private BigInteger lastNumber;

    private BigInteger[] lastFactors;

    private long hits;

    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (Objects.equals(i, lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (Objects.isNull(factors)) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(response, factors);
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return null;
    }
}
