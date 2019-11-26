package com.chaochaogu.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chaochao Gu
 * @date 2019/11/25
 */
public class APITest {

    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        DATE_FORMAT.get().format(new Date());
        int random = ThreadLocalRandom.current().nextInt(3);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("join" + i);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("tjoin" + i);
            }
        });
        // 是否为守护线程
        t1.setDaemon(true);
        t1.start();
        try {
            // wait for this thread to die
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        t1.interrupt();
        t1.isInterrupted();
        Thread.interrupted();
        Thread.State t1State = t1.getState();
        Thread thread = Thread.currentThread();
        t1.setPriority(Thread.NORM_PRIORITY);
        // 让步
        Thread.yield();
        t1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("defaultUncaughtExceptionHandler" + Thread.getDefaultUncaughtExceptionHandler());
            System.out.println("exception" + e);
        });
        // 可重入锁
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
        // 条件对象
        Condition condition = lock.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition.signal();
        condition.signalAll();
        try {
            // Object final method
            t1.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Object final method
        t1.notify();
        // Object final method
        t1.notifyAll();
        // 原子性
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.incrementAndGet();
        atomicLong.accumulateAndGet(9, (a, b) -> a + b);
        System.out.println(atomicLong.compareAndSet(10, 11));
        System.out.println(atomicLong);
        LongAccumulator adder = new LongAccumulator(Long::sum, 0);
        adder.accumulate(2);
        System.out.println(adder);
        if (lock.tryLock()) {
            // now the thread owns the lock
            try {
            } finally {
                lock.unlock();
            }
        } else {
            // do something else
        }
        // 读写锁
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Lock readLock = rwl.readLock();
        Lock writeLock = rwl.writeLock();
        // 对所有的获取方法加读锁
        readLock.lock();
        readLock.unlock();
        // 对所有的修改方法加写锁
        writeLock.lock();
        writeLock.unlock();
        Queue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        Queue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10, true);
        Queue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Queue<Delayed> delayQueue = new DelayQueue<>();
        try {
            ((LinkedBlockingQueue<String>) linkedBlockingQueue).offer("1", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrayBlockingQueue.peek();
        priorityBlockingQueue.poll();


    }
}
