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
 * Created by user on 2017/5/5.
 */

public class AlbumArrayAdapter  extends ArrayAdapter<AlbumItem> {

    Context context;

    public AlbumArrayAdapter(Context context, ArrayList<AlbumItem> items){
        super(context, 0, items);
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout itemlayout = null;
        if(convertView == null){
            itemlayout = (LinearLayout)inflater.inflate(R.layout.listitem, null);
        }   else{
            itemlayout = (LinearLayout) convertView;
        }
        AlbumItem item = (AlbumItem)getItem(position);

        ImageView actImg = (ImageView)itemlayout.findViewById(R.id.img_act);
        //actImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        actImg.setImageResource(item.getactImgId());
        TextView actName = (TextView)itemlayout.findViewById(R.id.tv_act);
        actName.setText(item.getactName());


        return itemlayout;
    }

}

