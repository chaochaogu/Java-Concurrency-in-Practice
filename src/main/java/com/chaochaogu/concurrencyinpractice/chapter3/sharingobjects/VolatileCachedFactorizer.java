package com.chaochaogu.concurrencyinpractice.chapter3.sharingobjects;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 使用指向不可变容器对象的volatile类型引用以缓存最新的结果
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
@ThreadSafe
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (Objects.isNull(factors)) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(res, factors);
    }

    private void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }
}
