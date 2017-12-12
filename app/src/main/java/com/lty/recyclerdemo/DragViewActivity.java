package com.lty.recyclerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class DragViewActivity extends AppCompatActivity implements StartDragListener {

    private RecyclerView recycler;
    private ItemTouchHelper itemTouchHelper = null;

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

        DragItemAdapter adapter = new DragItemAdapter(data, this);

        MyItemTouchHelperCallback callback = new MyItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);

        itemTouchHelper.attachToRecyclerView(recycler);

        recycler.setAdapter(adapter);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
