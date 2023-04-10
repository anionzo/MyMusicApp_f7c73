package com.example.mymusicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Context context;
    ArrayList<SongModel> songs;

    public SongAdapter(Context context, ArrayList<SongModel> items) {
        this.context = context;
        this.songs = items;
    }
    public void setData(ArrayList<SongModel> items) {
        this.songs = items;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel song = songs.get(position);
        if (song == null){
            return;
        }
        else {
            holder.imgItem.setImageResource(song.getLinkSong());
            holder.nameSongItem.setText(song.getNameSong());
            holder.nameAuthorItem.setText(song.getNameSinger());
        }
    }

    @Override
    public int getItemCount() {
        if (songs != null)
            return songs.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgItem;
        ImageView addPlaylist;
        TextView nameSongItem;
        TextView nameAuthorItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            nameSongItem = itemView.findViewById(R.id.name_song_item);
            nameAuthorItem = itemView.findViewById(R.id.name_author_item);
            addPlaylist = itemView.findViewById(R.id.add_playlist);

        }
    }
}
