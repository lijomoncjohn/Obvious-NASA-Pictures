package com.lijo.nasapictures.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lijo.nasapictures.R;
import com.lijo.nasapictures.model.ImageResponse;

import java.util.List;

public class ImageAdapater extends RecyclerView.Adapter<ImageAdapater.MyviewHolder> {
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
    public ImageAdapater.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_images,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapater.MyviewHolder holder, final int position) {
        Glide.with(context).load(dataList.get(position).getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
       ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.image);
        }
    }

}
