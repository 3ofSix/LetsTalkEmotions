package com.garrettvernon.letstalkemotions.child;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.garrettvernon.letstalkemotions.R;


public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";

    private Button btnNavFrag1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1);
        Log.d(TAG, "onCreateView: Started");

        //Add onClicklistener to the button
        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Demo with a toast
                Toast.makeText(getActivity(), "Going to fragment 1", Toast.LENGTH_SHORT).show();
                //tell viewPager where to go
                ((ChildMain)getActivity()).setViewPager(0);
            }
        });
        return view;
    }
}
