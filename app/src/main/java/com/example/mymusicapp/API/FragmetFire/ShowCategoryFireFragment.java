package com.example.mymusicapp.API.FragmetFire;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicapp.API.Firebase.Database;
import com.example.mymusicapp.Adapters.SliderAdapter;
import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.SingerModel;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ShowCategoryFireFragment extends Fragment {
    private TextView nameListPlay;
    private RecyclerView recyclerViewPlayItem;
    private ViewPager slider;
    private SliderAdapter sliderAdapter;
    private SongAdapter songAdapter;
    private ArrayList<SongModel> songModelArrayList = new ArrayList<>();
    //private TextView seeMorePlaylist;
    private ArrayList<SliderModel> sliderModel;
    LinearLayoutManager layoutManager;
    Database database = new Database();
    ArrayList<SingerModel> singerModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_category_fire, container, false);
        nameListPlay = view.findViewById(R.id.name_list_play);
        recyclerViewPlayItem = view.findViewById(R.id.list_play_item);
        slider =view.findViewById(R.id.slider);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        CategoryModel category = (CategoryModel) bundle.getSerializable("itemCategory");
        sliderModel = new ArrayList<>();
        // Set IMG của 1
        if (category != null){

            sliderModel.add(new SliderModel(category.getImgCategory(),category.getNameCategory()));
            sliderAdapter = new SliderAdapter(getContext(),sliderModel);
            slider.setAdapter(sliderAdapter);

            // Gán dữ liệu trống
            songAdapter = new SongAdapter(getContext(),songModelArrayList);
            layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
            recyclerViewPlayItem.setLayoutManager(layoutManager);

            // Gán dữ liệu cho Song Adapter'

            database.db.collection("singer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    SingerModel singerModel = new SingerModel(document.getId(),document.getString("nameSinger"),document.getString("imgSinger"));
                                    singerModels.add(singerModel);
                                }
                                songAdapter.cleanData();
                                database.db.collection("music")
                                        .whereEqualTo("idCategory", category.getIdCategory())
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String name = document.getString("idSinger");
                                                        for (SingerModel s : singerModels)
                                                        {
                                                            if(name.equals(s.getIdSinger())){
                                                                name = s.getNameSinger();
                                                            }
                                                        }
                                                        SongModel songModel = new SongModel(document.getId(), document.getString("idCategory"), document.getString("idAlbum"), document.getString("nameSong"), document.getString("linkImg"),name,document.getString("linkSong"));
                                                        songModelArrayList.add(songModel);
                                                    }
                                                    songAdapter.setData(songModelArrayList);
                                                    recyclerViewPlayItem.setAdapter(songAdapter);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });



            recyclerViewPlayItem.setAdapter(songAdapter);
        }
        else
            sliderModel.add(new SliderModel(getString(R.string.url_img1),"Không có âm nhạc!"));
    }
}