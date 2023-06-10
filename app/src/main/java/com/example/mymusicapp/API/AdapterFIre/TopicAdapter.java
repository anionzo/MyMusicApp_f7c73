package com.example.mymusicapp.API.AdapterFIre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapp.API.Firebase.Database;
import com.example.mymusicapp.Activities.ShowLisrCategoryActivity;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.TopicModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class TopicAdapter extends  RecyclerView.Adapter<TopicAdapter.ViewHonder> {

    private Context context;
    private ArrayList<TopicModel> topicModels;
    private ArrayList<CategoryModel> categoryModels;
    Database db = new Database();

    public TopicAdapter(Context context, ArrayList<TopicModel> topicModels) {
        this.context = context;
        this.topicModels = topicModels;

    }
    public void setDataTopic(ArrayList<TopicModel> topicModels){
        this.topicModels = topicModels;
        notifyDataSetChanged();
    }
    public void setDataCategory(ArrayList<CategoryModel> categoryModels){
        this.categoryModels = categoryModels;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHonder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic,parent,false);
        return new ViewHonder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHonder holder, int position) {
        TopicModel topicModel = topicModels.get(position);
        holder.nametopic.setText(topicModel.getNameTopic());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowLisrCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemTopic", topicModel);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (topicModels.size() != 0)
        return topicModels.size();
        return 0;
    }

    public class ViewHonder  extends RecyclerView.ViewHolder{
        TextView nametopic ;
        RelativeLayout layout;
        public ViewHonder(@NonNull View itemView) {
            super(itemView);
            nametopic = itemView.findViewById(R.id.name_topic);
            layout = itemView.findViewById(R.id.re_layout_topic);
        }
    }
}
