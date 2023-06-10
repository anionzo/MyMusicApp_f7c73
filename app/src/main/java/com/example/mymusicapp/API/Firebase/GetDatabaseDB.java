package com.example.mymusicapp.API.Firebase;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mymusicapp.Models.AlbumModel;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.SingerModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.Models.TopicModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class GetDatabaseDB {

    String link  = "gs://musicapp-f7c73.appspot.com";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public interface OnTopicFetchCompleteListener {
        void onTopicFetchComplete(ArrayList<TopicModel> topicModels);
        void onFetchFailure(Exception e);
    }
    public interface OnCategoryFetchCompleteListener {
        void onCategoryFetchComplete(ArrayList<CategoryModel> categoryModels);
        void onFetchFailure(Exception e);
    }
    public interface OnSongFetchCompleteListener {
        void onSongFetchComplete(ArrayList<SongModel> songModels);
        void onFetchFailure(Exception e);
    }
    public interface OnSingerFetchCompleteListener {
        void onSingerFetchComplete(ArrayList<SingerModel> singerModels);
        void onFetchFailure(Exception e);
    }
    public void getAllTopic(final OnTopicFetchCompleteListener listener) {
        db.collection("topic")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<TopicModel> topicModels = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TopicModel topicModel = new TopicModel(document.getId(), document.getString("nameTopic"), "");
                                topicModels.add(topicModel);
                            }
                            listener.onTopicFetchComplete(topicModels);
                        } else {
                            listener.onFetchFailure(task.getException());
                        }
                    }
                });
    }

    public ArrayList<TopicModel> getDB_Topic() {
        final ArrayList<TopicModel> models = new ArrayList<>();
        getAllTopic(new OnTopicFetchCompleteListener() {
            @Override
            public void onTopicFetchComplete(ArrayList<TopicModel> topicModels) {
                for (TopicModel topicModel : topicModels) {
                    System.out.println(topicModel.toString());
                    models.add(topicModel);
                }
            }
            @Override
            public void onFetchFailure(Exception e) {
                // Xử lý khi truy vấn gặp lỗi
                e.printStackTrace();
            }
        });
        return models;
    }

    public void getAllCategory(final OnCategoryFetchCompleteListener listener) {
        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<CategoryModel> categoryModels = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = new CategoryModel(document.getId(), document.getString("idTopic"), document.getString("nameCategory"), link + document.getString("imgCategory"));
                                categoryModels.add(categoryModel);
                            }
                            listener.onCategoryFetchComplete(categoryModels);
                        } else {
                            listener.onFetchFailure(task.getException());
                        }
                    }
                });
    }
    public void getAllSong(final OnSongFetchCompleteListener listener) {
        db.collection("music")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<SongModel> songModels = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SongModel songModel = new SongModel(document.getId(), document.getString("idCategory"), document.getString("idAlbum"), document.getString("nameSong"),link + document.getString("linkImg"),document.getString("idSinger"),document.getString("linkSong"));
                                songModels.add(songModel);
                            }
                            listener.onSongFetchComplete(songModels);
                        } else {
                            listener.onFetchFailure(task.getException());
                        }
                    }
                });
    }
    public void getAllSinger(final OnSingerFetchCompleteListener listener) {
        db.collection("singer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<SingerModel> singerModels = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SingerModel singerModel = new SingerModel(document.getId(),document.getString("nameSinger"),link + document.getString("imgSinger"));
                                singerModels.add(singerModel);
                            }
                            listener.onSingerFetchComplete(singerModels);
                        } else {
                            listener.onFetchFailure(task.getException());
                        }
                    }
                });
    }
}
