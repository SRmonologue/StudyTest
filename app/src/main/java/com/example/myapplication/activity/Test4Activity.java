package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.utils.CommonUtil;
import com.example.myapplication.adapter.ContactAdapter;
import com.example.myapplication.widget.ContactBean;
import com.example.myapplication.widget.CustomItemDecoration;
import com.example.myapplication.R;
import com.example.myapplication.widget.SideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gz on 2017/6/22.
 */

public class Test4Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SideBar side_bar ;
    private LinearLayoutManager mLayoutManager;
    private ContactAdapter mAdapter;
    List<ContactBean> nameList = new ArrayList<>();
    private CustomItemDecoration mDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        side_bar  = (SideBar) findViewById(R.id.side_bar);


        mAdapter = new ContactAdapter(R.layout.recycle_item_layout, nameList);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ContactBean item = mAdapter.getItem(position);
                Toast.makeText(Test4Activity.this, item.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        //侧边导航栏
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDecoration = new CustomItemDecoration(this);
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        initDatas();


        side_bar .setIndexChangeListener(new SideBar.indexChangeListener() {
            @Override
            public void indexChanged(String tag) {
                if (TextUtils.isEmpty(tag) || nameList.size() <= 0) return;
                for (int i = 0; i < nameList.size(); i++) {
                    if (tag.equals(nameList.get(i).getIndexTag())) {
                        mLayoutManager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    private void initDatas() {
        String[] names = {"孙尚香", "安其拉", "白起", "不知火舞", "@小马快跑", "_德玛西亚之力_", "妲己", "狄仁杰", "典韦", "韩信",
                "老夫子", "刘邦", "刘禅", "鲁班七号", "墨子", "孙膑", "孙尚香", "孙悟空", "项羽", "亚瑟",
                "周瑜", "庄周", "蔡文姬", "甄姬", "廉颇", "程咬金", "后羿", "扁鹊", "钟无艳", "小乔", "王昭君", "虞姬",
                "李元芳", "张飞", "刘备", "牛魔", "张良", "兰陵王", "露娜", "貂蝉", "达摩", "曹操", "芈月", "荆轲", "高渐离",
                "钟馗", "花木兰", "关羽", "李白", "宫本武藏", "吕布", "嬴政", "娜可露露", "武则天", "赵云", "姜子牙",};
        for (String name : names) {
            ContactBean bean = new ContactBean();
            bean.setName(name);
            nameList.add(bean);
        }
        //对数据源进行排序
        CommonUtil.sortData(nameList);
        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
        String tagsStr = CommonUtil.getTags(nameList);
        side_bar.setIndexStr(tagsStr);
        mDecoration.setDatas(nameList, tagsStr);
    }
}
