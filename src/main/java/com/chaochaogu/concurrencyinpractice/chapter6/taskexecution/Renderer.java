package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.chaochaogu.concurrencyinpractice.model.Throwables.launderThrowable;

/**
 * 使用CompletionService，使页面元素在下载完成后立即显示出来
 *
 * @author chaochao Gu
 * @date 2019/12/27
 */
public abstract class Renderer {

    private final ExecutorService executor;

    Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        ExecutorCompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        imageInfos.forEach((imageInfo) -> completionService.submit(imageInfo::downloadImage));
        renderText(source);
        try {
            for (int i = 0; i < imageInfos.size(); i++) {
                Future<ImageData> future = completionService.take();
                ImageData imageData = future.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
