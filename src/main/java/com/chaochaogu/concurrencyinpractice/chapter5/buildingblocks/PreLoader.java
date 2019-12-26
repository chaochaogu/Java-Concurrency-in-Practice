package com.chaochaogu.concurrencyinpractice.chapter5.buildingblocks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

/**
 * 使用FutureTask来提前加载稍后需要的数据
 *
 * @author chaochao Gu
 * @date 2019/12/23
 */
public class PreLoader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    private ProductInfo loadProductInfo() throws DataLoadException {
        // load productInfo...
        return null;
    }

    interface ProductInfo {
    }

    private class DataLoadException extends RuntimeException {
    }
}
