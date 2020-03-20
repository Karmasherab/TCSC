package com.kashey.conference.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;
import com.kashey.conference.domain.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private List<Post> mPostLIst;
    private  Context context;
    List<String> slider_image_list;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(mPostLIst.get(position).getDescription().contains("_n")){
            //Toast.makeText(context, "fgullfil", Toast.LENGTH_SHORT).show();
            String newName = mPostLIst.get(position).getDescription().replace("_n","\n");
            holder.postDesc.setText(newName);
        }

        if(mPostLIst.get(position).getImg_url().contains(",")){
            slider_image_list= Arrays.asList(mPostLIst.get(position).getImg_url().split("\\s*,\\s*"));
        }
        else
            slider_image_list.add(mPostLIst.get(position).getImg_url());

        SliderPagerAdapter sliderPagerAdapter= new SliderPagerAdapter(context, slider_image_list);
        holder.vp_slider.setAdapter(sliderPagerAdapter);
        holder.dots = new TextView[slider_image_list.size()];
        addBottomDots(0,holder.dots,holder);
        holder.vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position,holder.dots,holder);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void addBottomDots(int currentPage, TextView[] dots, ViewHolder holder) {


        holder.ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(context);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#000000"));
            holder.ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public int getItemCount() {
        return mPostLIst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postDesc;
        private ViewPager vp_slider;
        private LinearLayout ll_dots;
        private TextView[] dots;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //postImg=itemView.findViewById(R.id.post_pic);
            postDesc=itemView.findViewById(R.id.post_desc);
            vp_slider = itemView.findViewById(R.id.vp_slider);
            ll_dots = itemView.findViewById(R.id.ll_dots);
            slider_image_list = new ArrayList<>();

        }
    }
}
