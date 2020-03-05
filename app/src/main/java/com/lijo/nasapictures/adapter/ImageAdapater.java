package com.lijo.nasapictures.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lijo.nasapictures.R;
import com.lijo.nasapictures.activity.PictureDetailActivity;
import com.lijo.nasapictures.model.ImageResponse;
import com.lijo.nasapictures.viewholder.MyViewHolder;

import java.util.List;

public class ImageAdapater extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<ImageResponse> dataList;

    private static final String IMAGE_URL = "https://api.pubburps.com/v1/api/home/fetch/events/";

    public ImageAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<ImageResponse> exclusives) {
        this.dataList = exclusives;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_images,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .override(300, 300)
                .placeholder(R.drawable.lottie_loading_image)
                .into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PictureDetailActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
