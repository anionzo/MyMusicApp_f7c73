// Hiển thị danh sách các Category ....
package com.example.mymusicapp.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicapp.Adapters.CategoryAdapter;
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class ListCategoryFragment extends Fragment {

    SQLiteDatabase database = null;
    String databaseNameWithPath = "/data/data/com.example.mymusicapp/databases/mymusicapp.db";

    private CategoryAdapter categoryAdapter;
    private TextView nameListCategrory;
    private RecyclerView recyclerViewListCategory;
    LinearLayoutManager layoutManager;

    private ArrayList<CategoryModel> categoryList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list_category, container, false);
        nameListCategrory = view.findViewById(R.id.name_list_category);
        recyclerViewListCategory = view.findViewById(R.id.list_category);
        categoryList = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(getContext(),categoryList);
        layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewListCategory.setLayoutManager(layoutManager);
        categoryAdapter.setData(LoadListCategory());
        recyclerViewListCategory.setAdapter(categoryAdapter);
    }

    //DATA
    public ArrayList<CategoryModel> LoadListCategory(){
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        database = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath,null);
        Cursor c = null;
        c = database.query("Category",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){

            String idCa = c.getString(0);
            String idTopic = c.getString(1);
            String name = c.getString(2);
            String img = c.getString(3);
            CategoryModel category = new CategoryModel(idCa,idTopic,name,img);
            categoryModels.add(category);
            c.moveToNext();
        }
        c.close();
        return  categoryModels;
    }
}