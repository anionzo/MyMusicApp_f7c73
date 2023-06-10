package com.example.mymusicapp.API.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Database {
    public String link  = "gs://musicapp-f7c73.appspot.com";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
}
