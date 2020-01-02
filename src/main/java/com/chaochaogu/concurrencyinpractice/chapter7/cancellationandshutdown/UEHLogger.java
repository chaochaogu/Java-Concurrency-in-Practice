package com.chaochaogu.concurrencyinpractice.chapter7.cancellationandshutdown;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 将异常写入日志的UncaughtExceptionHandler
 *
 * @author chaochao Gu
 * @date 2020/1/2
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}
