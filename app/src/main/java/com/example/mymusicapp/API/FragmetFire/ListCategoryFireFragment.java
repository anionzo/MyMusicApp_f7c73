package com.example.mymusicapp.API.FragmetFire;

import static android.content.ContentValues.TAG;

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
import android.widget.TextView;

import com.example.mymusicapp.API.AdapterFIre.CategoryAdapteFire;
import com.example.mymusicapp.API.Firebase.Database;
import com.example.mymusicapp.Adapters.CategoryAdapter;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.Models.TopicModel;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ListCategoryFireFragment extends Fragment {


    private CategoryAdapteFire categoryAdapter;
    private TextView nameListCategory;
    private RecyclerView recyclerViewListCategory;
    private LinearLayoutManager layoutManager;
    private ArrayList<CategoryModel> categoryList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_category_fire, container, false);
        nameListCategory = view.findViewById(R.id.no);
        recyclerViewListCategory = view.findViewById(R.id.list_category_fire);
        categoryAdapter = new CategoryAdapteFire(getContext(), categoryList);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewListCategory.setLayoutManager(layoutManager);
        recyclerViewListCategory.setAdapter(categoryAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Database database = new Database();
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null){
        TopicModel topicModel = (TopicModel) bundle.getSerializable("itemTopic");


              database.db.collection("category")
                      .whereEqualTo("idTopic", topicModel.getIdTopic())
                      .get()
                      .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                          @Override
                          public void onComplete(@NonNull Task<QuerySnapshot> task) {
                              if (task.isSuccessful()) {
                                  for (QueryDocumentSnapshot document : task.getResult()) {
                                      CategoryModel categoryModel = new CategoryModel(
                                              document.getId(),
                                              document.getString("idTopic"),
                                              document.getString("nameCategory"),
                                              document.getString("imgCategory"));
                                      categoryModels.add(categoryModel);
                                      Log.d("Categorry", categoryModel.toString());
                                  }
                                  categoryAdapter.setData(categoryModels);
                                  recyclerViewListCategory.setAdapter(categoryAdapter);
                              } else {
                                  Log.d(TAG, "Error getting documents: ", task.getException());
                              }
                          }
                      });
          }else {
              database.db.collection("category")
                      .get()
                      .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                          @Override
                          public void onComplete(@NonNull Task<QuerySnapshot> task) {
                              if (task.isSuccessful()) {
                                  for (QueryDocumentSnapshot document : task.getResult()) {
                                      CategoryModel categoryModel = new CategoryModel(
                                              document.getId(),
                                              document.getString("idTopic"),
                                              document.getString("nameCategory"),
                                              document.getString("imgCategory"));
                                      categoryModels.add(categoryModel);
                                      Log.d("Categorry", categoryModel.toString());
                                  }
                                  categoryAdapter.setData(categoryModels);
                                  recyclerViewListCategory.setAdapter(categoryAdapter);
                              } else {
                                  Log.d(TAG, "Error getting documents: ", task.getException());
                              }
                          }
                      });
          }
        }
}