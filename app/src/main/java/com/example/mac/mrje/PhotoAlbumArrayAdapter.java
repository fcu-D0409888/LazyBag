package com.example.mac.mrje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017/5/7.
 */

class PhotoAlbumArrayAdapter extends ArrayAdapter<PhotoAlbumItem> {

    Context context;

    public PhotoAlbumArrayAdapter(Context context,
                             ArrayList<PhotoAlbumItem> items) {
        super(context, 0, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout itemlayout = null;
        if(convertView == null) {
            itemlayout = (LinearLayout)inflater.inflate
                    (R.layout.photolistitem, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }
        PhotoAlbumItem item = (PhotoAlbumItem)getItem(position);

        TextView tv_name = (TextView)itemlayout.
                findViewById(R.id.itemtv);
        tv_name.setText(item.name);

        ImageView iv = (ImageView)itemlayout.
                findViewById(R.id.itemiv);
        iv.setImageResource(item.imgid);
        return itemlayout;
    }

}
