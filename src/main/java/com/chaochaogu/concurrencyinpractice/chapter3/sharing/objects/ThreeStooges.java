package com.chaochaogu.concurrencyinpractice.chapter3.sharing.objects;

import com.chaochaogu.concurrencyinpractice.model.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 在可变对象基础上构建的不可变类
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
@Immutable
public final class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
