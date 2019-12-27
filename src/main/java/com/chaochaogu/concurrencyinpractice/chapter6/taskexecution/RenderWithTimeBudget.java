package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 在指定时间内获取广告信息
 *
 * @author chaochao Gu
 * @date 2019/12/27
 */
public class RenderWithTimeBudget {

    private static final Ad DEFAULT_AD = new Ad();
    private static final long TIME_BUDGET = 1000;
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    Page renderPageWithAd() {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = executor.submit(new FetchAdTask());
        // 在等待广告的同时显示页面
        Page page = renderPageBody();
        Ad ad;
        try {
            // 只等待指定的时间长度
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft, NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ad = DEFAULT_AD;
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }
        page.setAd(ad);
        return page;
    }

    Page renderPageBody() {
        return new Page();
    }

    static class Ad {
    }

    static class Page {
        public void setAd(Ad ad) {
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        public Ad call() {
            return new Ad();
        }
    }
}

