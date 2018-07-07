package com.garrettvernon.letstalkemotions;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ChildViewModel mChildViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAddChildView();
        initRecyclerView();

    }

    private void initAddChildView() {
        final View addChild = findViewById(R.id.add_Child_Card);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked");
                //Start AddChild Activity
                Intent intent = new Intent(getApplicationContext(), AddChild.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Set up Recycler View containing cards for
     * each child in the database
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.child_recycler_view);
        final ChildRecyclerViewAdapter adapter = new ChildRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChildViewModel = ViewModelProviders.of(this).get(ChildViewModel.class);
        mChildViewModel.getChildren().observe(this, new Observer<List<Child>>() {
            @Override
            public void onChanged(@Nullable final List<Child> children) {
                //upDate the cached copy of children in the adapter
                adapter.setChildren(children);
            }
        });

    }



}
