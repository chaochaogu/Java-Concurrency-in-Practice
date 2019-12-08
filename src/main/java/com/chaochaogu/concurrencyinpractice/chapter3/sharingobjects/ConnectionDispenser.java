package com.chaochaogu.concurrencyinpractice.chapter3.sharingobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用ThreadLocal来维持线程封闭性
 *
 * @author chaochao gu
 * @date 2019/12/7
 */
public class ConnectionDispenser {

    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder
            = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to acquire Connection, e");
        }
    });

    public Connection getConnection() {
        return connectionHolder.get();
    }
}
