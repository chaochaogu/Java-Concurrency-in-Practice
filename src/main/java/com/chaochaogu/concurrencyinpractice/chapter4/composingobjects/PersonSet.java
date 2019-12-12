package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 通过封闭机制来确保线程安全
 *
 * @author chaochao Gu
 * @date 2019/12/12
 */
@ThreadSafe
public class PersonSet {

    private final Set<Person> mySet = Sets.newHashSet();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    class Person {
    }
}
