package com.chaochaogu.concurrencyinpractice.chapter11.performanceandscalability;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 未对锁进行分解
 *
 * @author chaochao Gu
 * @date 2020/1/16
 */
@ThreadSafe
public class ServerStatusBeforeSplit {
    @GuardedBy("this")
    public final Set<String> users;
    @GuardedBy("this")
    public final Set<String> queries;

    public ServerStatusBeforeSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public synchronized void addUser(String user) {
        users.add(user);
    }

    public synchronized void addQuery(String query) {
        queries.add(query);
    }

    public synchronized void removeUser(String user) {
        users.remove(user);
    }

    public synchronized void removeQuery(String query) {
        queries.remove(query);
    }
}
