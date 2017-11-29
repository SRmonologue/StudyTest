package com.example.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.service.MessagerService;
import com.example.myapplication.service.MyIntentService;


/**
 * 描述：
 * Created by 9527 on 2017/7/13.
 */

public class Test8Activity extends AppCompatActivity {

    private Messenger mMessenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_loading);
        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test8Activity.this, MyIntentService.class);
                intent.putExtra("start", "haha");
                startService(intent);
            }
        });


        //绑定服务
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test8Activity.this, MessagerService.class);
                MyServiceConnection connection = new MyServiceConnection();
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        //发送消息
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMessenger == null) {
                    Toast.makeText(Test8Activity.this, "服务不可用！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Message message = new Message();
                message.obj = "长江长江我是黄河！";
                message.what = 0;
                try {
                    mMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private Messenger mOutMessager = new Messenger(new OutgoingHandler());

   class OutgoingHandler extends Handler{
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           Log.w("tag", msg.toString());
       }
   }

   class MyServiceConnection implements ServiceConnection{

       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           Toast.makeText(Test8Activity.this, "连接成功！", Toast.LENGTH_LONG).show();
           mMessenger = new Messenger(service);
           Message message = new Message();
           message.what = 1;
           message.obj = mOutMessager;
           try {
               mMessenger.send(message);
           } catch (RemoteException e) {
               e.printStackTrace();
           }
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
           Toast.makeText(Test8Activity.this, "连接已断开！", Toast.LENGTH_LONG).show();
       }
   }
}
