package com.example.gymguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Nullable;

public class ProfileActivity extends Fragment{



    Button logout;
    Button updateProfile;
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
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        logout = (Button)rootView.findViewById(R.id.btnLogout);
        updateProfile = (Button)rootView.findViewById(R.id.buttonUpdateStats);
        profilePhotos = rootView.findViewById(R.id.imageViewProfilePicture);
        userName = rootView.findViewById(R.id.textViewUserName);
        userGoal = rootView.findViewById(R.id.textViewUserWorkoutGoal);
        userCategories = rootView.findViewById(R.id.textViewWorkoutCategories);
        userDifficulty = rootView.findViewById(R.id.textViewWorkoutDifficulty);


        FirebaseUser user = auth.getInstance().getCurrentUser();
        if (!user.isAnonymous()) {
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
                                updateProfile.setText("UPDATE PROFILE");
                                logout.setText("LOGOUT");
                                userName.setText(userDetails.getUserName());

                                if(userDetails.getUserWorkoutGoal() != "") {
                                    userGoal.setText(userDetails.getUserWorkoutGoal());
                                } else{
                                    userGoal.setText("No goal set yet!");
                                }
                                if(userDetails.getWorkoutDifficulty() != "") {
                                    userDifficulty.setText(userDetails.getWorkoutDifficulty());
                                } else{
                                    userDifficulty.setText("No difficulty set yet!");
                                }


                                String catList = "";
                                boolean first = true;

                                if(userDetails.getWorkoutCategory().equals("")) {

                                    for (String category : userDetails.getWorkoutCategory()) {
                                        category = category.trim();
                                        if (first) {
                                            catList = category;
                                            first = false;
                                        } else {
                                            catList = catList + ", " + category;
                                        }
                                    }

                                    userCategories.setText(catList);
                                } else{
                                    userCategories.setText("No categories set yet!");
                                }



                                //Get Profile Picture From Storage
                                StorageReference storageRef = storage.getReference();
                                final StorageReference pathReference = storageRef.child("users/" + userDetails.getUserName() + "/profile.jpg");

                                pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Glide.with(ProfileActivity.this)
                                                .asBitmap()
                                                .load(pathReference)
                                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                                .skipMemoryCache(true)
                                                .into(profilePhotos);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // File not found
                                        Glide.with(ProfileActivity.this)
                                                .load(R.drawable.blank_profile)
                                                .into(profilePhotos);
                                    }
                                });

                                logout.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        auth.signOut();
                                        ProfileActivity.this.getActivity().finish();
                                        startActivity((new Intent(ProfileActivity.this.getActivity(), LoginActivity.class)));
                                    }
                                });

                                updateProfile.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        //go to updated activity and pass in user object
                                        Intent gotoUpdateActivityIntent = new Intent(rootView.getContext(), UpdateProfileActivity.class);
                                        gotoUpdateActivityIntent.putExtra("user", userDetails);
                                        startActivityForResult(gotoUpdateActivityIntent, 1);
                                    }
                                });


                            } else {
                                Log.d("TAG", "Current data: null");
                            }
                        }
                    });
        } else {
            //Set blank profile photo Guest User
            Glide.with(ProfileActivity.this)
                    .load(R.drawable.blank_profile)
                    .into(profilePhotos);

            logout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    auth.signOut();
                    ProfileActivity.this.getActivity().finish();
                    startActivity((new Intent(ProfileActivity.this.getActivity(), LoginActivity.class)));
                }
            });

            updateProfile.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    auth.signOut();
                    ProfileActivity.this.getActivity().finish();
                    startActivity((new Intent(ProfileActivity.this.getActivity(), LoginActivity.class)));
                }
            });
        }







        return rootView;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                //Get Profile Picture From Storage
                StorageReference storageRef = storage.getReference();
                final StorageReference pathReference = storageRef.child("users/" + userDetails.getUserName() + "/profile.jpg");

                pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(ProfileActivity.this)
                                .asBitmap()
                                .load(pathReference)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(profilePhotos);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // File not found
                        Glide.with(ProfileActivity.this)
                                .load(R.drawable.blank_profile)
                                .into(profilePhotos);
                    }
                });
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
