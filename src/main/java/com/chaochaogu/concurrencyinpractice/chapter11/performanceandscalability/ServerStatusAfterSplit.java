package com.chaochaogu.concurrencyinpractice.chapter11.performanceandscalability;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 将ServerStatus重新改写为使用锁分解技术
 *
 * @author chaochao Gu
 * @date 2020/1/16
 */
@ThreadSafe
public class ServerStatusAfterSplit {
    @GuardedBy("users")
    public final Set<String> users;
    @GuardedBy("queries")
    public final Set<String> queries;

    public ServerStatusAfterSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public void addUser(String user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public void addQuery(String query) {
        synchronized (queries) {
            queries.add(query);
        }
    }

    public void removeUser(String user) {
        synchronized (users) {
            users.remove(user);
        }
    }

    public void removeQuery(String query) {
        synchronized (queries) {
            queries.remove(query);
        }
    }
}
