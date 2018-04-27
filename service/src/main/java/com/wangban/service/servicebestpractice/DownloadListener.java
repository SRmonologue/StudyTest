package com.wangban.service.servicebestpractice;

/**
 * 描述：下载状态
 * Created by 9527 on 2018/4/27.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPased();

    void onCanceled();
}
