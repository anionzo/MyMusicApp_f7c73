package com.example.mymusicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapp.Models.SingerModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {

    Context context;
    ArrayList<SongModel> baihats ;

    public PlayNhacAdapter(Context context, ArrayList<SongModel> baihats ) {
        this.context = context;
        this.baihats = baihats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_song,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongModel songModel = baihats.get(position);
        holder.tencasi.setText(songModel.getNameSinger());
        holder.index.setText(position + 1 + "");
        holder.tenbaihat.setText(songModel.getNameSong());
    }

    @Override
    public int getItemCount() {
        return baihats.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView index, tencasi,tenbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.textViewplaysongindex);
            tencasi = itemView.findViewById(R.id.textViewnamesong);
            tenbaihat = itemView.findViewById(R.id.texviewnamesinger);
        }
    }
}
