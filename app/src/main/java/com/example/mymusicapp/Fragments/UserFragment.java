package com.example.mymusicapp.Fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mymusicapp.Activities.MainActivity;
import com.example.mymusicapp.Activities.RegisterActivity;
import com.example.mymusicapp.Activities.ShowPlayListActivity;
import com.example.mymusicapp.Models.UserModel;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserFragment extends Fragment {

    TextView TxtName, TxtBio,TxtEmail;
    ImageView ImgUser,EditProfile;
    Button PlayList, LogOut;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;

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
        TxtEmail = view.findViewById(R.id.txt_email);
        EditProfile = view.findViewById(R.id.edit_profile);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        return view;
    }
    // get user fibase
    public void User(final UserModel.OnUserModelCompleteListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference userRef = db.collection("users").document(user.getUid());
            userRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String userName = documentSnapshot.getString("userName");
                                String emailId = documentSnapshot.getString("emailId");
                                UserModel userModel = new UserModel(userName, emailId,"");
                                listener.onComplete(userModel);
                            } else {
                                Log.d(TAG, "Không có tìa liệu tìm kiếm");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Error getting document: " + e.getMessage());
                        }
                    });
        } else {
            Log.d(TAG, " Người dùng chưa đăng nhập");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Su dùn doc thong tin
        User(new UserModel.OnUserModelCompleteListener() {
            @Override
            public void onComplete(UserModel userModel) {
                // Xử lý dữ liệu của người dùng ở đây
                TxtName.setText(userModel.getUserName());
                TxtEmail.setText("Email: " + userModel.getEmail());
                Log.d("NAME Test", "User Name: " + userModel.getUserName() + ", Email Id: " + userModel.getEmail());
            }
        });
        PlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowPlayListActivity.class);
                startActivity(intent);

            }
        });
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
    private void uploadImageToFirebaseStorage(Uri imageUri) {
        // Tạo reference đến 1 folder trên Firebase Storage để lưu ảnh đại diện
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("avatar");

        // Tạo tên file mới cho ảnh đại diện theo định dạng "avatar_<timestamp>.jpg"
        String filename = "avatar_" + System.currentTimeMillis() + ".jpg";

        // Tạo reference đến file ảnh mới trên Firebase Storage
        StorageReference imageRef = storageRef.child(filename);

        // Upload ảnh lên Firebase Storage
        imageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Lấy URL của ảnh từ Firebase Storage và cập nhật thông tin người dùng
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(uri)
                                        .build();

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                currentUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Thành công
                                            Toast.makeText(getContext(), "Thêm ảnh thành công",Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Thất bại
                                            Toast.makeText(getContext(), "Thêm ảnh thất bại",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Thất bại
                        Toast.makeText(getContext(), "Thêm ảnh thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}