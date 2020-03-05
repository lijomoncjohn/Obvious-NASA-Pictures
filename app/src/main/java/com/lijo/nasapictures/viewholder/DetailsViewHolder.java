package com.lijo.nasapictures.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lijo.nasapictures.R;

public class DetailsViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title, explanation, date;

        public DetailsViewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.img_pic_details);
            title = (itemView).findViewById(R.id.tv_detail_title);
            date = (itemView).findViewById(R.id.tv_detail_date);
            explanation = (itemView).findViewById(R.id.tv_detail_explanation);
        }
}
