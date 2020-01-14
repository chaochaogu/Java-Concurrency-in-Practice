package com.chaochaogu.concurrencyinpractice.chapter11.performanceandscalability;

import com.chaochaogu.concurrencyinpractice.model.GuardedBy;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 将一个锁不必要地持有过长时间
 *
 * @author chaochao Gu
 * @date 2020/1/14
 */
@ThreadSafe
public class AttributeStore {
    @GuardedBy("this")
    private final Map<String, String> attributes = new HashMap<>();

    public synchronized boolean userLocationMatches(String name, String regexp) {
        String key = "users." + name + ".location";
        String location = attributes.get(key);
        if (Objects.isNull(location))
            return false;
        else
            return Pattern.matches(regexp, location);
    }
}
