package com.garrettvernon.letstalkemotions.child;


import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.garrettvernon.letstalkemotions.R;


public class ChildMain extends AppCompatActivity {
    private static final String TAG = "ChildMain";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_main);
        Log.d(TAG, "onCreate: Started");
        init();

    }

    private void init() {
        ChartFragment chartFragment = new ChartFragment();
        //Inflate the fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.child_chart_frame, chartFragment, getString(R.string.tag_fragment_chart));
        transaction.addToBackStack(getString(R.string.tag_fragment_chart));
        transaction.commit();
        HeartFragment heartFragment = new HeartFragment();
        FragmentTransaction heartTransaction = getSupportFragmentManager().beginTransaction();
        heartTransaction.replace(R.id.child_heart_frame, heartFragment, getString(R.string.tag_fragment_heart));
        heartTransaction.addToBackStack(getString(R.string.tag_fragment_heart));
        heartTransaction.commit();
    }
}
