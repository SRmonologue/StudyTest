package com.wangban.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 描述：
 * 04-26 15:55:55.729 1613-1613/com.wangban.service I/myIntentService: onCreate
 * 04-26 15:55:55.729 1613-1613/com.wangban.service I/myIntentService: onStartCommand
 * 04-26 15:55:55.729 1613-1613/com.wangban.service I/myIntentService: onStartCommand
 * 04-26 15:55:55.729 1613-1625/com.wangban.service I/myIntentService: do task1
 * 04-26 15:55:55.729 1613-1625/com.wangban.service I/myIntentService: do task2
 * 04-26 15:55:55.809 1613-1613/com.wangban.service I/myIntentService: onDestroy
 * <p>
 * 不能使用binderService启动服务,因为要通过onStartCommand把消息放进消息队列,binderService
 * 不调用这个方法
 * Created by 9527 on 2018/4/26.
 */

public class myIntentService extends IntentService {

    public myIntentService() {
        super("myIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Log.i("myIntentService", "do task1");
                break;
            case "task2":
                Log.i("myIntentService", "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        Log.i("myIntentService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("myIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("myIntentService", "onDestroy");
        super.onDestroy();
    }
}
