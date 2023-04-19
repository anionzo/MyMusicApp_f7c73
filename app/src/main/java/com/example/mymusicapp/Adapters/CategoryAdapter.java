// Hiển thị category trong RecyclerView
package com.example.mymusicapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymusicapp.Activities.PlaySongActivity;
import com.example.mymusicapp.Fragments.ShowCategoryFragment;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.HolderCategory> {

    Context context;
    ArrayList<CategoryModel> categorys;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categorys = categoryModels;
    }

    public void setData(ArrayList<CategoryModel> categorys){
        this.categorys = categorys;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categrory,parent,false);

        return new HolderCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        CategoryModel category = categorys.get(position);
        if (category == null){
            return;
        }else {
            Glide.with(context)
                    .load(category.getImgCategory())
                    .centerCrop()
                    .into(holder.categoryImg);
            holder.categoryTitle.setText(category.getNameCategory());
            holder.playCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ShowCategoryFragment.class);
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

    class HolderCategory extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryTitle;
        ImageView playCategory;

        public HolderCategory(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.category_img);
            categoryTitle = itemView.findViewById(R.id.category_title);
            playCategory = itemView.findViewById(R.id.play_list_category);

        }
    }
}
