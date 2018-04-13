package com.lty.recyclerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemDecActivity extends AppCompatActivity {

    private RecyclerView recycler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_dec);

        recycler = findViewById(R.id.recycler);
        // recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.addItemDecoration(new DividerGridDecoration(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            data.add("item" + i);
        }
        recycler.setAdapter(new ItemDecAdapter(data));

    }
}
