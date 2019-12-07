package com.chaochaogu.concurrencyinpractice.chapter2;


import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * 一个无状态的Servlet
 *
 * @author chaochao gu
 * @date 2019/12/3
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest();
        BigInteger[] factors = factor(i);
        encodeIntoResponse(response, factors);
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }

    private BigInteger extractFromRequest() {
        return null;
    }
}