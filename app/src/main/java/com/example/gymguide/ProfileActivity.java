package com.example.gymguide;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.annotation.Nullable;

public class ProfileActivity extends Fragment{



    Button logout;
    ImageView profilePhotos;
    TextView userName;
    TextView userGoal;
    TextView userCategories;
    TextView userDifficulty;

    User userDetails;

    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseStorage storage;

    public ProfileActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        logout =(Button)rootView.findViewById(R.id.btnLogout);
        profilePhotos = rootView.findViewById(R.id.imageViewProfilePicture);
        userName = rootView.findViewById(R.id.textViewUserName);
        userGoal = rootView.findViewById(R.id.textViewUserWorkoutGoal);
        userCategories = rootView.findViewById(R.id.textViewWorkoutCategories);
        userDifficulty = rootView.findViewById(R.id.textViewWorkoutDifficulty);


        FirebaseUser user = auth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection("users")
                    .document(user.getUid())
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot snapshot,
                                            @Nullable FirebaseFirestoreException e) {

                            if (e != null) {
                                Log.w("TAG", "Listen failed.", e);
                                return;
                            }

                            if (snapshot != null && snapshot.exists()) {
                                Log.d("TAG", "Current data: " + snapshot.getData());
                                userDetails = snapshot.toObject(User.class);
                                userName.setText(userDetails.getUserName());
                                userGoal.setText(userDetails.getUserWorkoutGoal());
                                userDifficulty.setText(userDetails.getWorkoutDifficulty());

                                String catList = "";
                                boolean first = true;

                                for (String category : userDetails.getWorkoutCategory()) {
                                    category = category.trim();
                                    if(first) {
                                        catList = category;
                                        first = false;
                                    } else {
                                        catList = catList + ", " + category;
                                    }
                                }

                                userCategories.setText(catList);

                                //Get Profile Picture From Storage
                                StorageReference storageRef = storage.getReference();
                                StorageReference pathReference = storageRef.child("users/" + userDetails.getUserName() + "/profile.jpg");

                                // Download directly from StorageReference using Glide
                                Glide.with(ProfileActivity.this)
                                        .load(pathReference)
                                        .into(profilePhotos);


                            } else {
                                Log.d("TAG", "Current data: null");
                            }
                        }
                    });



        }





        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                auth.signOut();
                ProfileActivity.this.getActivity().finish();
                startActivity((new Intent(ProfileActivity.this.getActivity(), LoginActivity.class)));
            }
        });

        return rootView;


    }
}
