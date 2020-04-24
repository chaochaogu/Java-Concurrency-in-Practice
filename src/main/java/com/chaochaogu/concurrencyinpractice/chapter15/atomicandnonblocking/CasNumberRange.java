package com.chaochaogu.concurrencyinpractice.chapter15.atomicandnonblocking;

import com.chaochaogu.concurrencyinpractice.model.Immutable;
import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过CAS来维持包含多个变量的不变性条件
 *
 * @author chaochao Gu
 * @date 2020/4/24
 */
@ThreadSafe
public class CasNumberRange {
    @Immutable
    private static class IntPair {
        // INVARIANT: lower <= upper
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldValue = values.get();
            if (i > oldValue.upper)
                throw new IllegalArgumentException("Can't set lower to " + i + "> upper" + oldValue.upper);
            IntPair newValue = new IntPair(i, oldValue.upper);
            if (values.compareAndSet(oldValue, newValue))
                return;
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldValue = values.get();
            if (i < oldValue.lower)
                throw new IllegalArgumentException("Can't set upper to " + i + "< lower " + oldValue.lower);
            IntPair newValue = new IntPair(oldValue.lower, i);
            if (values.compareAndSet(oldValue, newValue))
                return;
        }
    }
}
