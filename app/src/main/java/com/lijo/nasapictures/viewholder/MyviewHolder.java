package com.lijo.nasapictures.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lijo.nasapictures.R;

public class MyviewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.photo);
            title = (itemView).findViewById(R.id.title);
        }
}
