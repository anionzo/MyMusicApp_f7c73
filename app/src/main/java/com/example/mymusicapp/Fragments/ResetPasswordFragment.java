package com.example.mymusicapp.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.renderscript.Script;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymusicapp.Activities.MainActivity;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    private TextView goBack;
    private FrameLayout frameLayout;
    private Drawable errorIcon;
    private EditText email;
    private TextView responseMessage;
    private Button resetPasswordButton;
    private ProgressBar resetPassProgressBar;
    private FirebaseAuth mAuth;

    public ResetPasswordFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        // Khai báo id
        goBack = view.findViewById(R.id.go_back);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);

        email = view.findViewById(R.id.email);
        resetPasswordButton = view.findViewById(R.id.resetPassButton);
        resetPassProgressBar = view.findViewById(R.id.resetPassProgressBar);
        responseMessage = view.findViewById(R.id.responseMessage);
        errorIcon = getResources().getDrawable(R.drawable.ic_error);
        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorIcon.setBounds(0,0,errorIcon.getIntrinsicWidth(),errorIcon.getIntrinsicHeight());
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            resetPassProgressBar.setVisibility(View.VISIBLE);
            String mail =email.getText().toString();
            mAuth.sendPasswordResetEmail(mail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            resetPassProgressBar.setVisibility(View.INVISIBLE);
                            if(task.isSuccessful()){
                                responseMessage.setText("Kiểm tra hộp thư Email của bạn!");
                                responseMessage.setTextColor(getResources().getColor(R.color.green));
                            }else{
                                responseMessage.setText("Email không tồn tại!");
                                setButtonResetPassTrue();

                            }
                            resetPassProgressBar.setVisibility(View.INVISIBLE);
                            responseMessage.setVisibility(View.VISIBLE);
                        }
                    });
        }else {
            email.setError("Email không hợp lệ!",errorIcon);
            setButtonResetPassTrue();

        }
    }
    private void setButtonResetPassFalse(){
        resetPasswordButton.setEnabled(false);
        resetPasswordButton.setTextColor(getResources().getColor(R.color.transWhite));
    }
    private void setButtonResetPassTrue(){
        resetPasswordButton.setEnabled(true);
        resetPasswordButton.setTextColor(getResources().getColor(R.color.white));
    }
    private void checkInputs() {
        if(!email.getText().toString().isEmpty()){
           setButtonResetPassTrue();
        }else{
          setButtonResetPassFalse();
        }
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}