package com.example.mymusicapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<SliderModel> sliderModelList;

    public SliderAdapter(Context context, ArrayList<SliderModel> sliderModelList) {
        this.context = context;
        this.sliderModelList = sliderModelList;
    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_item,null);
        TextView sliderTitle = view.findViewById(R.id.slider_title);
        ImageView sliderImg= view.findViewById(R.id.slider_img);
        Glide.with(context)
                        .load(sliderModelList.get(position).getImg())
                                .centerCrop()
                                        .into(sliderImg);

        sliderTitle.setText(sliderModelList.get(position).getSlideName());
        container.addView(view);
        return  view;
    }
}
