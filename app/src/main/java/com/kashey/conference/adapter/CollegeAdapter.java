package com.kashey.conference.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;
import com.kashey.conference.domain.College;
import com.kashey.conference.domain.Members;

import java.util.List;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.ViewHolder> {

    private List<College> mCollegeLIst;
    private Context context;
    public CollegeAdapter(List<College> mCollegeLIst, Context context) {

        this.mCollegeLIst=mCollegeLIst;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_college_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mCollegeLIst.get(position).getCollegename()+"\n"+mCollegeLIst.get(position).getPlace());
        Glide.with(context).load(mCollegeLIst.get(position).getImg_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mCollegeLIst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.college_pic);
            textView=itemView.findViewById(R.id.college_desc);
        }
    }

}
