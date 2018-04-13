package com.lty.recyclerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lty.recyclerdemo.adapter.ItemButtonAdapter;
import com.lty.recyclerdemo.adapter.ItemGridAdapter;
import com.lty.recyclerdemo.layout.FocusKeepRecyclerView;
import com.lty.recyclerdemo.layout.MyGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class TianchiActivity extends AppCompatActivity {


    private FocusKeepRecyclerView mFocusKeepRecyclerView;
    private FocusKeepRecyclerView mFocusKeepRecyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianchi);

        mFocusKeepRecyclerView = findViewById(R.id.focusKeepRecyclerView);
        mFocusKeepRecyclerView1 = findViewById(R.id.focusKeepRecyclerView1);


        mFocusKeepRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add("item" + i);
        }
        mFocusKeepRecyclerView.setAdapter(new ItemButtonAdapter(data));


        mFocusKeepRecyclerView1.setLayoutManager(new MyGridLayoutManager(this, 6));
        List<String> data1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data1.add("item" + i);
        }
        mFocusKeepRecyclerView1.setAdapter(new ItemGridAdapter(data1));

    }
}
