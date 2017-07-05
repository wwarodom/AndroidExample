package com.warodom.picassogrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by wwaro on 7/5/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mUrls;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, String[] urls) {
        mContext = context;
        mUrls = urls;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView)
                    convertView.findViewById(R.id.grid_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mUrls[position]).into(viewHolder.imageView);
        return convertView;
    }

    public class ViewHolder {
        ImageView imageView;
    }
}
