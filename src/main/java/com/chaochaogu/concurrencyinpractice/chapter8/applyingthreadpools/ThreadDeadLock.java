package com.chaochaogu.concurrencyinpractice.chapter8.applyingthreadpools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在单线程Executor中任务发生死锁（不要这么做）
 *
 * @author chaochao Gu
 * @date 2020/1/2
 */
public class ThreadDeadLock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            // read file
            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁 --- 由于任务在等待子任务的结束
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // render page
            return "";
        }
    }
}
