package com.lijo.nasapictures.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lijo.nasapictures.R;
import com.lijo.nasapictures.model.ImageResponse;
import com.lijo.nasapictures.viewholder.DetailsViewHolder;

import java.util.List;

public class DetailsAdapater extends RecyclerView.Adapter<DetailsViewHolder> {
    Context context;
    List<ImageResponse> dataList;

    private static final String IMAGE_URL = "https://api.pubburps.com/v1/api/home/fetch/events/";

    public DetailsAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<ImageResponse> exclusives) {
        this.dataList = exclusives;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details,parent,false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, final int position) {
        Glide.with(holder.image.getContext())
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .override(300, 300)
                .placeholder(R.drawable.lottie_loading_image).into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
        holder.explanation.setText(dataList.get(position).getExplanation());
        holder.date.setText(dataList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

//    public class MyviewHolder extends RecyclerView.ViewHolder {
//       ImageView image;
//       TextView title, explanation, date;
//
//        public MyviewHolder(View itemView) {
//            super(itemView);
//            image = (itemView).findViewById(R.id.img_pic_details);
//            title = (itemView).findViewById(R.id.tv_detail_title);
//            date = (itemView).findViewById(R.id.tv_detail_date);
//            explanation = (itemView).findViewById(R.id.tv_detail_explanation);
//        }
//    }

}
