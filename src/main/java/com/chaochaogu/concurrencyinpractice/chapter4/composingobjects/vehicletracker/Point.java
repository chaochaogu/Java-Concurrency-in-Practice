package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker;

import com.chaochaogu.concurrencyinpractice.model.Immutable;

/**
 * 在DelegatingVehicleTracker中使用的不可变Point类
 *
 * @author chaochao Gu
 * @date 2019/12/19
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
