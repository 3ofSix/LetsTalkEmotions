/*
 * Recycler view used to display child objects from the database
 */

package com.garrettvernon.letstalkemotions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ChildViewAdapter";
    private final LayoutInflater mInflator;
    private List<Child> mChild;

    public ChildRecyclerViewAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.child_list, parent, false);
//        ViewHolder holder = new ViewHolder(itemView);
        return new ViewHolder(itemView);
//        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:called");
        if (mChild != null){
            Child current = mChild.get(position);
            holder.childName.setText(current.getFirstName());
            //TODO add OnClickListener
        }else {
            holder.childName.setText("No Child");
        }

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:called");
        if (mChild != null){
            return mChild.size();
        }else {
            return 0;
       }
    }

    public void setChildren(List<Child> children) {
        mChild = children;
        notifyDataSetChanged();
    }

    /**
     * View Holder class
     * Holds each child card in memory for recycling
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout childLayout;
        TextView childName;

        public ViewHolder(View itemView) {
            super(itemView);
            childLayout = itemView.findViewById(R.id.child_card_list);
            childName = itemView.findViewById(R.id.name_child_card);
        }
    }
}
