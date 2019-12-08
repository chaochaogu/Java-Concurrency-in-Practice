package com.chaochaogu.concurrencyinpractice.chapter3.sharingobjects;

/**
 * 使内部的可变状态逸出（不要这么做）
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
public class UnsafeStates {

    private String[] states = new String[]{"AA", "BB", "CC"};

    public String[] getStates() {
        return states;
    }
}
