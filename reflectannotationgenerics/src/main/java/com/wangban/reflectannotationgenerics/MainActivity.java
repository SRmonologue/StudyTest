package com.wangban.reflectannotationgenerics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // testClassLoader();
        //testGenerics();
        //testArrayListGenerics();
        // testReflect();
        //testGetConstructor();
        testGetField();
    }

    /**
     * 反射操作类的字段
     * getFields()获取类的字段，但是获取不到私有字段
     * getDeclaredFields()获取类的所有字段
     */
    private void testGetField() {
        Class<Girl> aClass = Girl.class;
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println("----> field = " + field);
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("-----> declaredField = " + declaredField);
        }

        //获取指定的字段及其type
        try {
            Field name = aClass.getDeclaredField("name");
            Class<?> type = name.getType();
            System.out.println("-----> field " + name + ",type = " + type);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射获取类的构造器
     * getConstructors()获取类的构造器，但获取不到私有的
     * getDeclaredConstructors()获取类所有的构造器
     * getDeclaredConstructor()获取指定的构造器
     */
    private void testGetConstructor() {
        Class<Girl> girlClass = Girl.class;
        Constructor<?>[] constructors = girlClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("----->constructor=" + constructor);
        }

        Constructor<?>[] declaredConstructors = girlClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("----->declaredConstructors=" + declaredConstructor);
        }

        try {
            Constructor<Girl> declaredConstructor = girlClass.getDeclaredConstructor(String.class, Integer.class);
            declaredConstructor.setAccessible(true);//取消语言访问检查
            Girl girl = declaredConstructor.newInstance("zhangsan", Integer.valueOf(23));
            System.out.println("-----> Girl = " + girl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射的三种方式
     */
    private void testReflect() {
        //第一种方式
        Class<Girl> aClass = Girl.class;
        System.out.println("----->" + aClass.getName());

        // 第二种方式
        Girl girl = new Girl();
        Class<? extends Girl> aClass1 = girl.getClass();
        System.out.println("----->" + aClass1.getName());

        // 第三种方式
        try {
            Class<?> aClass2 = Class.forName("com.wangban.reflectannotationgenerics.Girl");
            System.out.println("----->" + aClass2.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void testArrayListGenerics() {
        List<Integer> mList = new ArrayList<>();
        mList.add(2222);
        mList.add(5555);
        try {
            Method method = mList.getClass().getMethod("add", Object.class);
            method.invoke(mList, "sbLiXian");
            for (int i = 0; i < mList.size(); i++) {
                System.out.println("mList.get(" + i + ") = " + mList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testGenerics() {
        Class<? extends ArrayList> aClass = new ArrayList<Integer>().getClass();
        Class<? extends ArrayList> aClass1 = new ArrayList<String>().getClass();
        boolean isEquals = (aClass == aClass1);
        System.out.println("-----> isEquals=" + isEquals);
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

    /**
     * 自定义泛型方法
     */
    public static <T> T genericMethod1(T t) {
        return null;
    }

    public static <K, V> K genericMethod2(K k, V v) {
        return null;
    }

    public static <K, V> String genericMethod3(K k, V v) {
        return null;
    }

    /**
     * 自定义泛型接口
     */

    public interface UserInfo<T> {
        public void printUserInfo(T t);
    }

    public class UserInfoImpl<T> implements UserInfo<T> {
        @Override
        public void printUserInfo(T t) {

        }
    }

    /**
     * 自定义泛型类
     */
    public class Collection<K, V> {
        private K key;
        private V value;

        private K getValue(K k) {
            return null;
        }

        private void printValue(V v) {

        }
    }
}
