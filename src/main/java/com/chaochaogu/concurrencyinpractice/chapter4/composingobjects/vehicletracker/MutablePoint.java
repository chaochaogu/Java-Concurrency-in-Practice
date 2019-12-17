package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker;

import com.chaochaogu.concurrencyinpractice.model.NotThreadSafe;

/**
 * 与Java.awt.Point类似的可变Point类（不要这么做）
 *
 * @author chaochao Gu
 * @date 2019/12/13
 */
@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = x;
        this.y = y;
    }

}
