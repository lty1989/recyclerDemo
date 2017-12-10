package com.lty.recyclerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WrapRecyclerActivity extends AppCompatActivity {

    private WrapRecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_recycler);

        recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        List<String> data = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            data.add("item" + i);
        }
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);


        TextView textView = new TextView(this);
        textView.setText("Hello");
        textView.setLayoutParams(params);
        recycler.addHeaderView(textView);
        recycler.setAdapter(new ItemDecAdapter(data));

        // recycler.setAdapter(new ItemDecAdapter(data));

    }
}
