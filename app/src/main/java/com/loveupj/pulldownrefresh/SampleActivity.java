package com.loveupj.pulldownrefresh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {
    MyListView listView;
    MyAdapter adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        initView();
    }

    private void initView() {
        listView = findViewById(R.id.listview);
        list = new ArrayList<>();
        list.add("start");
        for (int i = 0; i < 50; i++) {
            list.add("我们都是开发者");
        }

        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list.add("刷新后添加的内容");
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });

    }

}
