package com.example.mymusicapp.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class SignInFragment extends Fragment {
    private TextView dontHaveAnAccount;
    private TextView resetPassword;
    private FrameLayout frameLayout;
    private EditText email;
    private EditText password;
    private Button signInButton;
    private FirebaseAuth mAuth;
    private Drawable errorIcon;

    private ProgressBar signInProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Khai bao id
        dontHaveAnAccount = view.findViewById(R.id.dont_have_account);
        resetPassword = view.findViewById(R.id.reset_password);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        signInButton =view.findViewById(R.id.signInButton);
        signInProgress = view.findViewById(R.id.signInProgress);
        errorIcon = getResources().getDrawable(R.drawable.ic_error);

        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorIcon.setBounds(0,0,errorIcon.getIntrinsicWidth(),errorIcon.getIntrinsicHeight());
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment() );
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new ResetPasswordFragment());
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

        password.addTextChangedListener(new TextWatcher() {
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
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInButton.setEnabled(false);
                signInButton.setTextColor(getResources().getColor(R.color.transWhite));
                signInWithFirebase();
            }
        });
    }

    private void signInWithFirebase() {
        if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            signInProgress.setVisibility(View.VISIBLE);
            String mail =email.getText().toString();
            String pass =password.getText().toString();
            mAuth.signInWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signInProgress.setVisibility(View.INVISIBLE);
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                Toast.makeText(getContext(),"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }else {
                                Toast.makeText(getContext(),"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();

                                setButtonSignInTrue();
                            }
                        }
                    });

        }else {
            email.setError("Email không hợp lệ!",errorIcon);
            setButtonSignInTrue();
        }
    }

    private void setButtonSignInFalse(){
        signInButton.setEnabled(false);
        signInButton.setTextColor(getResources().getColor(R.color.transWhite));
    }
    private void setButtonSignInTrue(){
        signInButton.setEnabled(true);
        signInButton.setTextColor(getResources().getColor(R.color.white));
    }
    private void checkInputs() {
        if(!email.getText().toString().isEmpty() || !password.getText().toString().isEmpty()){
                setButtonSignInTrue();
        }else setButtonSignInFalse();


    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right, R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}