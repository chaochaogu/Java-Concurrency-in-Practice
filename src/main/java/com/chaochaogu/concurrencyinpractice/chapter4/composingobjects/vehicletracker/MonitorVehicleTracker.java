package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 基于监视器模式的车辆追踪
 *
 * @author chaochao Gu
 * @date 2019/12/13
 */
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return Objects.isNull(loc) ? null : loc;
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (Objects.isNull(loc)) {
            throw new IllegalArgumentException("no such ID :" + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String, MutablePoint> result = new HashMap<>();
        locations.forEach(result::put);
        return Collections.unmodifiableMap(result);
    }

}
