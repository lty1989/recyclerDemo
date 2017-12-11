package com.lty.recyclerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DragViewActivity extends AppCompatActivity {

    private RecyclerView recycler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        List<String> data = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            data.add("item" + i);
        }
        recycler.setAdapter(new ItemDecAdapter(data));

    }
}
