package com.lijo.nasapictures.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lijo.nasapictures.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.photo);
            title = (itemView).findViewById(R.id.title);
        }
}
