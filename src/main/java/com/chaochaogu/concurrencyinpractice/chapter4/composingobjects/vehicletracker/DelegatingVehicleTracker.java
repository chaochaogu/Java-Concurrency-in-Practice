package com.chaochaogu.concurrencyinpractice.chapter4.composingobjects.vehicletracker;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将线程安全委托给ConcurrentHashMap
 *
 * @author chaochao Gu
 * @date 2019/12/19
 */
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentHashMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    /**
     * 返回locations的静态拷贝而非实时拷贝
     *
     * @return Map
     */
    public Map<String, Point> getLocationsAsStatic() {
        return Collections.unmodifiableMap(new ConcurrentHashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (Objects.isNull(locations.replace(id, new Point(x, y)))) {
            throw new IllegalArgumentException("invalid vehicle name :" + id);
        }
    }
}

