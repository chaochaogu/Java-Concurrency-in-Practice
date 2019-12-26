package com.chaochaogu.concurrencyinpractice.model;

/**
 * 强制将未检查的Throwable转换为RuntimeException
 *
 * @author chaochao Gu
 * @date 2019/12/26
 */
public class Throwables {
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("not unchecked " + t);
        }
    }
}
