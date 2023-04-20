package com.example.mymusicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mymusicapp.Models.PlaylistModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class ShowCategoryAdapter extends RecyclerView.Adapter<ShowCategoryAdapter.ViewHolder> {
    private Context context;
    ArrayList<PlaylistModel> items;

    public ShowCategoryAdapter(Context context, ArrayList<PlaylistModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.fragment_show_category,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistModel playlistModel = items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameListPlay;
        ViewPager  slider;
        RecyclerView listPlayItem;
        //TextView seeMorePlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameListPlay = itemView.findViewById(R.id.name_list_play);
            slider       = itemView.findViewById(R.id.slider);
            listPlayItem = itemView.findViewById(R.id.list_play_item);
            //seeMorePlaylist = itemView.findViewById(R.id.see_more_playlists);

        }
    }
}
