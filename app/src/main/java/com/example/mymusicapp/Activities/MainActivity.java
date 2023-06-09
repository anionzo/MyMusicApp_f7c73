package com.example.mymusicapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.API.Firebase.GetDatabaseDB;
import com.example.mymusicapp.Fragments.MenuFragment;
import com.example.mymusicapp.Models.TopicModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fragmentMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMenu = findViewById(R.id.main_frame_layout);
        setFragment(new MenuFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentMenu.getId(), fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        // xử lý tại đây
        //  hiển thị một dialog hỏi người dùng có muốn thoát không
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát ứng dụng");
        builder.setMessage("Bạn có chắc chắn muốn thoát không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // kết thúc Activity và thoát ứng dụng
                finishAffinity();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // đóng dialog và không kết thúc Activity
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}