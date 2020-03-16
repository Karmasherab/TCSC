package com.kashey.conference.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;
import com.kashey.conference.domain.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> mPostLIst;
    private  Context context;
    public PostAdapter(List<Post> mPostLIst, Context context) {
        this.mPostLIst=mPostLIst;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_post_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mPostLIst.get(position).getDescription().contains("_n")){
            //Toast.makeText(context, "fgullfil", Toast.LENGTH_SHORT).show();
            String newName = mPostLIst.get(position).getDescription().replace("_n","\n");
            postDesc.setText(newName);
        }
        //postDesc.setText(mPostLIst.get(position).getDescription());
        Glide.with(context).load(mPostLIst.get(position).getImg_url()).into(postImg);
    }

    @Override
    public int getItemCount() {
        return mPostLIst.size();
    }
    ImageView postImg;
    TextView postDesc;
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postImg=itemView.findViewById(R.id.post_pic);
            postDesc=itemView.findViewById(R.id.post_desc);
        }
    }
}
