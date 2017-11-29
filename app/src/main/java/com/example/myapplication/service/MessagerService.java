package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 描述：接受到Activity消息后会返回一条消息
 * Created by 9527 on 2017/11/29.
 */

public class MessagerService extends Service {
    private Messenger mMessenger = new Messenger(new IncomingHandle());
    private Messenger mActivityMessenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder = mMessenger.getBinder();
        return binder;
    }

    //定义一个handle对象，处理activity发送过来的消息
    class IncomingHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.w("tag", msg.toString());
                    if (mActivityMessenger != null) {
                        Message message = new Message();
                        message.what = 2;
                        message.obj = "地瓜地瓜我是土豆";
                        try {
                            mActivityMessenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:
                    mActivityMessenger = (Messenger) msg.obj;
                    Log.w("tag", "已经获取从Activity发送的Messenger对象");
                    break;
                default:
                    break;
            }
        }
    }
}
