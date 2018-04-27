package com.wangban.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * 静态注册，监听开机的广播
 */
public class OpenMachineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("我就试试看！");
        builder.show();
        Toast.makeText(context,"我开机了你知道吗?",Toast.LENGTH_SHORT).show();
    }
}
