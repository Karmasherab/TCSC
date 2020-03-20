package com.kashey.conference.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;
import com.kashey.conference.domain.Members;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {
    private List<Members> mMembersLIst;
    private Context context;
    public MembersAdapter(List<Members> mMembersLIst, Context context) {
    this.mMembersLIst=mMembersLIst;
    this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_members_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(mMembersLIst.get(position).getName().contains("_n")){
            //Toast.makeText(context, "fgullfil", Toast.LENGTH_SHORT).show();
            String newName = mMembersLIst.get(position).getName().replace("_n","\n");
            holder.textView.setText(newName+mMembersLIst.get(position).getDesignation());
            //holder.textView.setText("\nDesignation-"+mMembersLIst.get(position).getDesignation());
        }

        Glide.with(context).load(mMembersLIst.get(position).getImg_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mMembersLIst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.members_pic);
            textView=itemView.findViewById(R.id.members_desc);
        }
    }

}

