package com.loveupj.pulldownrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipeRefreshLayoutActivity extends FragmentActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lv;
    private ArrayAdapter adapter;
    private List<String> list;
    private View footerView;

    private int visibleLastIndex;//用来可显示的最后一条数据的索引

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x101:
                    if (swipeRefreshLayout.isRefreshing()) {
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);//设置不刷新
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        swipeRefreshLayout = findViewById(R.id.srl);
        lv = findViewById(R.id.listview);

//        footerView = getLayoutInflater().inflate(R.layout.loading_layout, null);
//        lv.addFooterView(footerView);

//        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (adapter.getCount() == visibleLastIndex && scrollState == SCROLL_STATE_IDLE) {
//                    new LoadDataThread().start();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                visibleLastIndex = firstVisibleItem + visibleItemCount - 1;//减去最后一个加载中那条
//            }
//        });

        list = new ArrayList<>();
        list.addAll(Arrays.asList("Java", "php", "C++", "C#", "IOS", "html", "C", "J2ee", "j2se", "VB", ".net", "Http", "tcp", "udp", "www"));

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        lv.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new LoadDataThread().start();
            }
        });
    }

    /**
     * 模拟加载数据的线程
     */
    class LoadDataThread extends Thread {
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x101);//通过handler发送一个更新数据的标记
        }

        private void initData() {
            list.addAll(Arrays.asList("Json1", "XML2", "UDP3", "http4"));
        }
    }

}
