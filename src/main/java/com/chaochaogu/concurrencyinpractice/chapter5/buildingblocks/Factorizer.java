package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer.Computable;
import com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks.memoizer.Memoizer;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * 在因式分解servlet中使用Memoizer来缓存结果
 *
 * @author chaochao gu
 * @date 2019/12/25
 */
@ThreadSafe
public class Factorizer extends GenericServlet implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = this::factor;

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    private BigInteger[] factor(BigInteger arg) {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(res, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(res, "factorization interrupted");
        }
    }

    private void encodeError(ServletResponse res, String errorString) {
    }

    private void encodeIntoResponse(ServletResponse res, BigInteger[] compute) {
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return BigInteger.valueOf(7L);
    }
}
