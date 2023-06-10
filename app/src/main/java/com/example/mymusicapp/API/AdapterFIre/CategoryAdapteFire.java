package com.example.mymusicapp.API.AdapterFIre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.API.ActivityFire.ChiTietCategoryActivity;
import com.example.mymusicapp.Activities.ShowListActivity;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.PlaylistModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class CategoryAdapteFire extends   RecyclerView.Adapter<CategoryAdapteFire.ViewHolder>{
    Context context;
    ArrayList<CategoryModel> categorys;

    public CategoryAdapteFire(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categorys = categoryModels;
    }
    public void setData(ArrayList<CategoryModel> categorys){
        this.categorys = categorys;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_categrory,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel category = categorys.get(position);
        if (category == null){
            return;
        }else {
            Glide.with(context)
                    .load(category.getImgCategory())
                    .centerCrop()
                    .into(holder.categoryImg);

            holder.categoryTitle.setText(category.getNameCategory());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietCategoryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemCategory", category);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (categorys != null)
            return categorys.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryTitle;
        ImageView playCategory;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.category_img);
            categoryTitle = itemView.findViewById(R.id.category_title);
            playCategory = itemView.findViewById(R.id.play_list_category);
            relativeLayout = itemView.findViewById(R.id.layout_category);
        }
    }
}
