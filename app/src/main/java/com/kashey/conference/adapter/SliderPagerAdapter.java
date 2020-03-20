package com.kashey.conference.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.kashey.conference.R;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Context activity;
    List<String> image_arraylist;

    public SliderPagerAdapter(Context activity, List<String> image_arraylist) {
        this.activity = activity;
        this.image_arraylist = image_arraylist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_slider, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        Glide.with(activity.getApplicationContext())
                .load(image_arraylist.get(position))
                .into(im_slider);
//        Picasso.with(activity.getApplicationContext())
//                .load(image_arraylist.get(position))
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(im_slider);


        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
