package com.chaochaogu.concurrencyinpractice.model;

import java.lang.annotation.*;

/**
 * @author chaochao Gu
 * @date 2020/1/7
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Deadlock {
}
