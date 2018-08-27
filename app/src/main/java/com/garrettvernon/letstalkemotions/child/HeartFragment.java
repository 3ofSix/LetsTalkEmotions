package com.garrettvernon.letstalkemotions.child;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garrettvernon.letstalkemotions.R;


public class HeartFragment extends Fragment {
    private static final String TAG = "HeartFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_heart, container, false);
        Log.d(TAG, "onCreateView: Started");
        return view;
    }
}
