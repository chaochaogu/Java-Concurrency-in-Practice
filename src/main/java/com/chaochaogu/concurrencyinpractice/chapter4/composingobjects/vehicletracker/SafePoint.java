package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

/**
 * 线程安全且可变的Point类
 *
 * @author chaochao Gu
 * @date 2019/12/19
 */
@ThreadSafe
public class SafePoint {

    private int x, y;

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
