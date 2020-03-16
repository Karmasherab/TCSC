package com.kashey.conference.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;
import com.kashey.conference.domain.College;
import com.kashey.conference.domain.Gallery;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<Gallery> mGalleryLIst;
    private Context context;

    public GalleryAdapter(List<Gallery> mGalleryList, Context context)
    {
        this.mGalleryLIst=mGalleryList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_gallery_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(mGalleryLIst.get(position).getImg_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mGalleryLIst.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
