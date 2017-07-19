package com.warodom.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by staff on 19-Jul-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Actor> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;

    public RecyclerViewAdapter(Context context, List<Actor> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor hero = mData.get(position);
        holder.tvName.setText(hero.getName());
        holder.ivResId.setBackgroundResource(hero.getResId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Actor getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvName;
        protected ImageView ivResId;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivResId = (ImageView) itemView.findViewById(R.id.ivResId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null )
                mItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
