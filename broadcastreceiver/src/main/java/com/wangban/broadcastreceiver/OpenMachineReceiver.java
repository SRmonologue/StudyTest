package com.wangban.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 静态注册，监听开机的广播
 */
public class OpenMachineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"我开机了你知道吗?",Toast.LENGTH_SHORT).show();
    }
}
