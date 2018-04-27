package com.example.myapplication.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;


/**
 * 描述：
 * Created by 9527 on 2017/7/13.
 */

public class Test10Activity extends AppCompatActivity {


    private WebView mWebView;
    private FHandle mFHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test10);
        mWebView = (WebView) findViewById(R.id.webview);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("http://ip.cn/");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.contains("logo.gif")) {
                    InputStream mIs = null;

                    try {
                        mIs = getApplicationContext().getAssets().open("images/error.png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    WebResourceResponse response = new WebResourceResponse("image/png", "utf-8", mIs);
                    return response;
                }
                return super.shouldInterceptRequest(view, url);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("logo.gif")) {
                    InputStream mIs = null;

                    try {
                        mIs = getApplicationContext().getAssets().open("images/error.png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    WebResourceResponse response = new WebResourceResponse("image/png", "utf-8", mIs);
                    return response;
                }
                return super.shouldInterceptRequest(view, request);
            }
        });

        mFHandle = new FHandle(this);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = "AA";
                mFHandle.sendMessage(msg);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 2;
                msg.obj = "BB";
                mFHandle.sendMessage(msg);
            }
        }.start();
    }

    private static class FHandle extends Handler {

        private WeakReference<Activity> mReference;

        public FHandle(Activity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d("gz", "收到现成1的消息");
                    break;
                case 2:
                    Log.d("gz", "收到线程2的消息");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFHandle.removeCallbacksAndMessages(null);
    }
}
