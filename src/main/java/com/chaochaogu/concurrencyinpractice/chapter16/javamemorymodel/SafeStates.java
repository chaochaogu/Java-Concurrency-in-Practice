package com.chaochaogu.concurrencyinpractice.chapter16.javamemorymodel;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象的初始化安全性
 *
 * @author chaochao Gu
 * @date 2020/4/27
 */
@ThreadSafe
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        /*...*/
        states.put("wyoming", "WY");
    }

    public String getAbbreviation(String s) {
        return states.get(s);
    }
}
