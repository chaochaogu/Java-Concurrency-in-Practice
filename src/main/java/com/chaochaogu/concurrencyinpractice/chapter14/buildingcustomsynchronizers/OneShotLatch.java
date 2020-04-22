package com.chaochaogu.concurrencyinpractice.chapter14.buildingcustomsynchronizers;

import com.chaochaogu.concurrencyinpractice.model.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用AbstractQueuedSynchronizer实现的二元闭锁
 *
 * @author chaochao Gu
 * @date 2020/4/22
 */
@ThreadSafe
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        protected int tryAcquireShared(int ignored) {
            // succeed if latch is open (state == 1), else fail
            return (getState() == 1) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignored) {
            setState(1); // Latch is now open
            return true; // Other threads may now be able to acquire
        }
    }
}
