package com.csxm.tagdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String testStr = "测试测试测试测试测试";
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            stringList.add(testStr.substring(0, 1 + (new java.util.Random().nextInt(testStr.length() - 1))));
        }

        TagViewByViewGroup tagViewByViewGroup = findViewById(R.id.tagview_by_viewgroup);
        tagViewByViewGroup.setViewData(stringList);

        final TagViewByLinearLayout tagViewByLinearLayout = findViewById(R.id.tagview_by_linearlayout);
        tagViewByLinearLayout.post(new Runnable() {
            @Override
            public void run() {
                tagViewByLinearLayout.setViewData(stringList);
            }
        });


    }
}
