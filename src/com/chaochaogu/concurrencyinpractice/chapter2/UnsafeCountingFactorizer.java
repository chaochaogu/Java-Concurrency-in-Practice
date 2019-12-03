package com.chaochaogu.concurrencyinpractice.chapter2;

import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

import java.math.BigInteger;

/**
 * 在没有同步的情况下统计已处理请求数量的Servlet（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/3
 */
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
        ++count;
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
