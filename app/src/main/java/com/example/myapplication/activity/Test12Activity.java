package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 描述：Rxjava 创建操作符
 * Created by 9527 on 2017/9/21.
 */

public class Test12Activity extends AppCompatActivity {

    private static final String TAG = "Rxjava";
    private Integer i = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test12);
        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Test12Activity.this, "范魁东", Toast.LENGTH_LONG).show();
            }
        });




        /**
         * Rx基本创建方式
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始链接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对next事件开始响应" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对error事件开始响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对complete事件开始响应");
            }
        });

        /**
         *快速创建，最多只能发送10个参数
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "响应事件：" + integer);
                    }
                });

        /**
         * 快速创建，发送数组
         */

        final Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Observable.fromArray(items).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "响应事件" + integer);
            }
        });

        /**
         * 快速创建，发送集合
         */

        List<Integer> mList = new ArrayList<>();
        mList.add(1);
        mList.add(2);
        mList.add(3);
        mList.add(4);
        mList.add(5);
        Observable.fromIterable(mList).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "事件响应" + integer);
            }
        });

        /**
         * 延时创建
         */
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        i = 15;
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "延迟响应" + integer);
            }
        });

        /**
         * 延迟指定时间，发送一个数值
         */
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "延迟5秒显示响应结果");
                    }
                });

        /**
         * 每隔指定时间就发送事件
         */
        Observable.interval(3, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "等一下就来：" + aLong);
                    }
                });
    }
}
