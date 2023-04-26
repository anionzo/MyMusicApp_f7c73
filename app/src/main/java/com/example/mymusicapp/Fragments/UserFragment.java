package com.example.mymusicapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusicapp.Activities.MainActivity;
import com.example.mymusicapp.Activities.RegisterActivity;
import com.example.mymusicapp.Activities.SplashActivity;
import com.example.mymusicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFragment extends Fragment {

    TextView TxtName, TxtBio;
    ImageView ImgUser;
    Button PlayList, LogOut;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        TxtName = view.findViewById(R.id.txt_name);
        TxtBio = view.findViewById(R.id.txt_bio);
        ImgUser = view.findViewById(R.id.img_user);
        PlayList = view.findViewById(R.id.play_list_user);
        LogOut = view.findViewById(R.id.log_out);
        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                mAuth.signOut();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                mAuth.signOut();
                if(currentUser != null){
                    intent = new Intent(getContext(), MainActivity.class);
                    getActivity().finish();
                }else {
                    intent = new Intent(getContext(), RegisterActivity.class);
                    getActivity().finish();
                }
                startActivity(intent);
            }
        });
    }
}