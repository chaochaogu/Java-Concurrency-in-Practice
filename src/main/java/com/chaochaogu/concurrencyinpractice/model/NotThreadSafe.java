package com.chaochaogu.concurrencyinpractice.model;

import java.lang.annotation.*;

/**
 * @author chaochao gu
 * @date 2019/12/2
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotThreadSafe {
}
