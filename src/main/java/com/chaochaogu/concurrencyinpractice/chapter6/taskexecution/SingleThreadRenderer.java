package com.chaochaogu.concurrencyinpractice.chapter6.taskexecution;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 串行地渲染页面元素
 *
 * @author chaochao Gu
 * @date 2019/12/26
 */
public abstract class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = Lists.newArrayList();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
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
