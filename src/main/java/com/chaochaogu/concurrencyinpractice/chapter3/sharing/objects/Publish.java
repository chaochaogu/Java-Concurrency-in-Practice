package com.chaochaogu.concurrencyinpractice.chapter3.sharing.objects;

import java.util.HashSet;
import java.util.Set;

/**
 * 发布一个对象
 *
 * @author chaochao gu
 * @date 2019/12/5
 */
public class Publish {

    public static Set<String> set;

    public void initialize() {
        set = new HashSet<String>();
    }
}
