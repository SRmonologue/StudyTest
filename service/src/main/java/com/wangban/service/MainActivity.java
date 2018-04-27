package com.wangban.service;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wangban.service.servicebestpractice.DownloadService;

public class MainActivity extends AppCompatActivity {

    private DownloadService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mDownloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * IntentService
         */
        //        //请求1
        //        Intent intent1 = new Intent("cn.cn");
        //        Bundle bundle1 = new Bundle();
        //        bundle1.putString("taskName", "task1");
        //        intent1.putExtras(bundle1);
        //        startService(intent1);
        //        //请求2
        //        Intent intent2 = new Intent("cn.cn");
        //        Bundle bundle2 = new Bundle();
        //        bundle2.putString("taskName", "task2");
        //        intent2.putExtras(bundle2);
        //        startService(intent2);


        /**
         * 前台服务
         */
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        /**
         *完整的下载实例
         */
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        findViewById(R.id.btn_download_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDownloadBinder == null) {
                    return;
                }
                String url = "http://t-1.tuzhan.com/9d0d7b56dd52/c-2/l/2015/01/24/01/913b67eda05644f29a0dd51319e33cb6.jpg";
                mDownloadBinder.startDownload(url);
            }
        });

        findViewById(R.id.btn_download_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDownloadBinder == null) {
                    return;
                }
                mDownloadBinder.pasueDownload();
            }
        });

        findViewById(R.id.btn_download_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDownloadBinder == null) {
                    return;
                }
                mDownloadBinder.cancelDownload();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "拒绝权限将无法使用程序!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
