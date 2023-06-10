package com.example.mymusicapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusicapp.API.Firebase.Database;
import com.example.mymusicapp.API.Firebase.GetDatabaseDB;
import com.example.mymusicapp.API.AdapterFIre.TopicAdapter;
import com.example.mymusicapp.Models.TopicModel;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FragmentTopic extends Fragment {
    View view;
    GetDatabaseDB database = new GetDatabaseDB();
    Database db = new Database();
    TopicAdapter topicAdapter;
    private RecyclerView recyclerViewListTop;
    LinearLayoutManager layoutManager;
    private ArrayList<TopicModel> topics = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_topic, container, false);
        recyclerViewListTop = view.findViewById(R.id.recycler_topc_List);
        topics = new ArrayList<>();

        topicAdapter = new TopicAdapter(getContext(),topics);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewListTop.setLayoutManager(layoutManager);
        db.db.collection("topic").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TopicModel top = new TopicModel(document.getId(), document.getString("nameTopic"), "");
                                topics.add(top);
                            }
                            // Gán dữ liệu cho adapter và gán adapter cho RecyclerView sau khi truy vấn hoàn thành

                            Log.d("Topic firebase", topics.toString());
                            topicAdapter.setDataTopic(topics);
                            recyclerViewListTop.setAdapter(topicAdapter);
                        } else {
                            // Xử lý khi truy vấn gặp lỗi
                            Log.d("Topic firebase", "Error getting documents: " + task.getException());
                        }
                    }
                });

        Log.d("Topic firebase sss 0","s");

    }
}