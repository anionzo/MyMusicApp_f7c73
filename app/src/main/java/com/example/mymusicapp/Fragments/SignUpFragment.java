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
import com.example.mymusicapp.Activities.RegisterActivity;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    private TextView alreadyHaveAccount ;
    private FrameLayout frameLayout;
    private EditText email;
    private EditText userName;
    private EditText password;
    private EditText comfirmPassword;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private Drawable errorIcon;
    private FirebaseFirestore db;
    private ProgressBar signUpProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        // Khai baos id
        alreadyHaveAccount = view.findViewById(R.id.already_have_account);
        frameLayout = getActivity().findViewById(R.id.register_frame_layout);
        email =view.findViewById(R.id.email);
        userName =view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        comfirmPassword =view.findViewById(R.id.confirm_password);
        signUpButton = view.findViewById(R.id.signUpButton);
        signUpProgress = view.findViewById(R.id.signUpProgress);
        errorIcon = getResources().getDrawable(R.drawable.ic_error);

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorIcon.setBounds(0,0,errorIcon.getIntrinsicWidth(),errorIcon.getIntrinsicHeight());
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });
        userName.addTextChangedListener(new TextWatcher() {
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
        comfirmPassword.addTextChangedListener(new TextWatcher() {
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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpWithFirebase();
                setButtonSignUpFalse();
            }
        });
    }

    private void signUpWithFirebase() {
        if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            if(password.getText().toString().equals(comfirmPassword.getText().toString())){
                signUpProgress.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                signUpProgress.setVisibility(View.INVISIBLE);
                                if(task.isSuccessful()){
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("userName",userName.getText().toString());
                                    user.put("emailId",email.getText().toString());
                                    db.collection("users")
                                            .document(task.getResult().getUser().getUid())
                                            .set(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                                                    getActivity().startActivity(intent);
                                                    getActivity().finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                                    setButtonSignUpTrue();
                                                }
                                            });
                                }else {
                                    Toast.makeText(getContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    setButtonSignUpTrue();
                                }
                            }
                        });
            }else {
                comfirmPassword.setError("Password không khớp!",errorIcon);
                setButtonSignUpTrue();
            }
        }else {
            email.setError("Email không hợp lệ!",errorIcon);
            setButtonSignUpTrue();;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void setButtonSignUpFalse(){
        signUpButton.setEnabled(false);
        signUpButton.setTextColor(getResources().getColor(R.color.transWhite));
    }
    private void setButtonSignUpTrue(){
        signUpButton.setEnabled(true);
        signUpButton.setTextColor(getResources().getColor(R.color.white));
    }
    private void checkInputs(){
        if(!userName.getText().toString().isEmpty() || !email.getText().toString().isEmpty()||
                !password.getText().toString().isEmpty() && (password.getText().toString().length() >= 6) ||
                !comfirmPassword.getText().toString().isEmpty()) {
            setButtonSignUpTrue();
        }
        else  setButtonSignUpFalse();
    }
}