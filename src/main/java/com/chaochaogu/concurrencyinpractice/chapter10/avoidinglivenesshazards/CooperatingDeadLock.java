package com.chaochaogu.concurrencyinpractice.chapter10.avoidinglivenesshazards;

import com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker.Point;
import com.chaochaogu.concurrencyinpractice.model.Deadlock;
import com.chaochaogu.concurrencyinpractice.model.GuardedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * 在相互协作对象之间的锁顺序死锁（不要这么做）
 *
 * @author chaochao Gu
 * @date 2020/1/7
 */
public class CooperatingDeadLock {
    class Taxi {
        @GuardedBy("this")
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        @Deadlock
        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        @Deadlock
        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {
        }
    }
}
