package com.example.myapplication.activity;

import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SlidingAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：抽屉
 * Created by 9527 on 2017/12/28.
 */

public class Test15Activity extends AppCompatActivity implements CalendarView.OnDateSelectedListener {
    private View bottomSheet;
    private BottomSheetBehavior behavior;
    private SlidingAdapter mAdapter;
    private CalendarView mCalendarView;


    public WifiManager wifiManager;//管理wifi
    public ConnectivityManager connectManager;  //管理网络连接
    public NetworkInfo netInfo;//网络连接
    public WifiInfo wifiInfo; //wifi
    public DhcpInfo dhcpInfo;
    List<ScanResult> list;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test15);

        try {
            wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);        //获得系统wifi服务
            connectManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            netInfo = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            dhcpInfo = wifiManager.getDhcpInfo();
            wifiInfo = wifiManager.getConnectionInfo();
            String path = wifiInfo.getMacAddress();

            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            String wifiProperty = "当前连接Wifi信息如下：" + wifiInfo.getSSID() + '\n' +
                    "ip:" + FormatString(dhcpInfo.ipAddress) + '\n' +
                    "mask:" + FormatString(dhcpInfo.netmask) + '\n' +
                    "netgate:" + FormatString(dhcpInfo.gateway) + '\n' +
                    "dns:" + FormatString(dhcpInfo.dns1);
            Log.e("cx", "Mac地址 = " + path);

                        for (int i=0;i<10;i++){
            wifiManager.startScan();
            list = wifiManager.getScanResults();
            Log.e("cx", "wifi信息 = " + list.toString());
            Log.e("cx","状态 = "+wifiManager.startScan());
                        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateSelectedListener(this);
        initData();

        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = behavior.getState();
                if (state == 4) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (state == 3) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        //        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
        //            @Override
        //            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
        //                String state = "null";
        //                switch (newState) {
        //                    case 1:
        //                        state = "STATE_DRAGGING";
        //                        break;
        //                    case 2:
        //                        state = "STATE_SETTLING";
        //                        break;
        //                    case 3:
        //                        state = "STATE_EXPANDED";
        //                        break;
        //                    case 4:
        //                        state = "STATE_COLLAPSED";
        //                        break;
        //                    case 5:
        //                        state = "STATE_HIDDEN";
        //                        break;
        //                }
        //                Log.d("MainActivity", "newState:" + state);
        //            }
        //
        //            @Override
        //            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        //                //                Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
        //            }
        //        });


        //        ImageView fab = (ImageView) findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //            }
        //        });


    }

    private void initData() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        mCalendarView.setSchemeDate(schemes);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onDateSelected(Calendar calendar) {
        Toast.makeText(this, calendar.getYear() + "-" + calendar.getMonth() + "-"
                + calendar.getDay(), Toast.LENGTH_SHORT).show();
    }

    public String FormatString(int value) {
        String strValue = "";
        byte[] ary = intToByteArray(value);
        for (int i = ary.length - 1; i >= 0; i--) {
            strValue += (ary[i] & 0xFF);
            if (i > 0) {
                strValue += ".";
            }
        }
        return strValue;
    }

    public byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }

    //将搜索到的wifi根据信号强度从强到弱进行排序
    private void sortByLevel(ArrayList<ScanResult> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 1; j < list.size(); j++) {
                if (list.get(i).level < list.get(j).level)    //level属性即为强度
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
    }
}