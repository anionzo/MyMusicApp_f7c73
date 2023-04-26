package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mymusicapp.Fragments.HomeFragment;
import com.example.mymusicapp.Fragments.SignInFragment;
import com.example.mymusicapp.R;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private void initView(){
        frameLayout = findViewById(R.id.register_frame_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setFragment(new SignInFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
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