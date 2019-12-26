package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

/**
 * 使用Future等待图像下载
 *
 * @author chaochao Gu
 * @date 2019/12/26
 */
public abstract class FutureRenderer {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = () -> {
            List<ImageData> result = Lists.newArrayList();
            imageInfos.forEach(imageInfo -> result.add(imageInfo.downloadImage()));
            return result;
        };
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = future.get();
            imageData.forEach(this::renderImage);
        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();
            // 由于不需要结果，因此取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            launderThrowable(e.getCause());
        }
    }

    interface ImageData {
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);
}
