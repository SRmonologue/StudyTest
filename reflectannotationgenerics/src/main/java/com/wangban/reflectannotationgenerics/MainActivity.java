package com.wangban.reflectannotationgenerics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testClassLoader();
    }

    /**
     * ClassLoader
     */
    private void testClassLoader() {
        try {
            //PathClassLoader用于加载已经安装的apk中的资源，比如dex
            Class clazz = Class.forName("com.wangban.huituban.huituban");
            ClassLoader classLoader = clazz.getClassLoader();
            System.out.println("-----> classLoader=" + classLoader);

            //BootClassLoader用于加载系统层级的类
            Class<?> aClass = Class.forName("java.lang.String");
            ClassLoader classLoader1 = aClass.getClassLoader();
            System.out.println("----> classLoader=" + classLoader1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
