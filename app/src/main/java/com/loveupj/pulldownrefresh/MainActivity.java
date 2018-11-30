package com.loveupj.pulldownrefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSample;
    Button btnSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnSample = findViewById(R.id.btn_sample);
        btnSwipe = findViewById(R.id.btn_swiperefreshlayout);

        btnSample.setOnClickListener(new onViewClick());
        btnSwipe.setOnClickListener(new onViewClick());
    }

    class onViewClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btn_sample:
                    intent.setClass(MainActivity.this, SampleActivity.class);
                    break;
                case R.id.btn_swiperefreshlayout:
                    intent.setClass(MainActivity.this, SwipeRefreshLayoutActivity.class);
                    break;
            }

            startActivity(intent);
        }
    }

}
