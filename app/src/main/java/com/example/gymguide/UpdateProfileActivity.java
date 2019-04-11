package com.example.gymguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfileActivity extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 3;

    Button uploadPhoto;
    Button updateProfileU;
    ImageView profilePhoto;
    TextView textViewUserName;
    EditText workoutGoal;
    CheckBox checkBoxArms;
    CheckBox checkBoxBack;
    CheckBox checkBoxCardio;
    CheckBox checkBoxCore;
    CheckBox checkBoxLegs;
    RadioButton radioDiffE;
    RadioButton radioDiffM;
    RadioButton radioDiffH;

    FirebaseFirestore db;
    FirebaseStorage storage;

    boolean complete = true;

    User userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        uploadPhoto = findViewById(R.id.buttonUploadPhoto);
        updateProfileU = findViewById(R.id.btnUpdateProfile);
        profilePhoto = findViewById(R.id.imageViewProfilePictureU);
        textViewUserName = findViewById(R.id.textViewUserNameU);
        workoutGoal = findViewById(R.id.editTextWorkoutGoal);
        checkBoxArms = findViewById(R.id.checkBoxArms);
        checkBoxBack = findViewById(R.id.checkBoxBack);
        checkBoxCardio = findViewById(R.id.checkBoxCardio);
        checkBoxCore = findViewById(R.id.checkBoxCore);
        checkBoxLegs = findViewById(R.id.checkBoxLegs);
        radioDiffE = findViewById(R.id.radioButtonDiffE);
        radioDiffM = findViewById(R.id.radioButtonDiffM);
        radioDiffH = findViewById(R.id.radioButtonDiffH);

        userDetails = (User) getIntent().getSerializableExtra("user");

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        textViewUserName.setText(userDetails.getUserName());
        workoutGoal.setText(userDetails.getUserWorkoutGoal());

        //set checkboxes for current categories
        if(userDetails.getWorkoutCategory() != null) {
            for (String category : userDetails.getWorkoutCategory()) {
                category = category.trim();

                switch (category) {
                    case "Arms":
                        checkBoxArms.setChecked(true);
                        break;
                    case "Back":
                        checkBoxBack.setChecked(true);
                        break;
                    case "Cardio":
                        checkBoxCardio.setChecked(true);
                        break;
                    case "Core":
                        checkBoxCore.setChecked(true);
                        break;
                    case "Legs":
                        checkBoxLegs.setChecked(true);
                        break;
                }
            }
        }

        //Set current difficulty
        switch (userDetails.getWorkoutDifficulty()) {
            case "Easy" :
                radioDiffE.setChecked(true);
                break;
            case "Medium" :
                radioDiffM.setChecked(true);
                break;
            case "Hard" :
                radioDiffH.setChecked(true);
                break;
        }

        //Get Profile Picture From Storage
        StorageReference storageRef = storage.getReference();
        final StorageReference pathReference = storageRef.child("users/" + userDetails.getUserName() + "/profile.jpg");

        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(UpdateProfileActivity.this)
                        .load(pathReference)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(profilePhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // File not found
                Glide.with(UpdateProfileActivity.this)
                        .load(R.drawable.blank_profile)
                        .into(profilePhoto);
            }
        });

        //update database fields
        updateProfileU.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (complete) {

                    //update workout goal

                    userDetails.setUserWorkoutGoal(workoutGoal.getText().toString());

                    //update category
                    ArrayList<String> category = new ArrayList<String>();

                    if (checkBoxArms.isChecked()) {
                        category.add("Arms");
                    }
                    if (checkBoxBack.isChecked()) {
                        category.add("Back");
                    }
                    if (checkBoxCardio.isChecked()) {
                        category.add("Cardio");
                    }
                    if (checkBoxCore.isChecked()) {
                        category.add("Core");
                    }
                    if (checkBoxLegs.isChecked()) {
                        category.add("Legs");
                    }

                    userDetails.setWorkoutCategory(category);

                    //update difficulty
                    if (radioDiffH.isChecked()) {
                        userDetails.setWorkoutDifficulty("Hard");
                    } else if (radioDiffM.isChecked()) {
                        userDetails.setWorkoutDifficulty("Medium");
                    } else {
                        userDetails.setWorkoutDifficulty("Easy");
                    }


                    db.collection("users") //Table
                            .document(userDetails.userID)
                            .set(userDetails) //object
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                    Intent returnIntent = new Intent();
                                    setResult(Activity.RESULT_OK, returnIntent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });


                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Upload In Progress, Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //update profile photo
        uploadPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            complete = false;

            //write photo to Firebase Storage
                Glide.with(this)
                        .asBitmap()
                        .load(selectedImage)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                final byte[] writeData = baos.toByteArray();

                                //Display selected image
                                Glide.with(UpdateProfileActivity.this)
                                        .asBitmap()
                                        .load(writeData)
                                        .into(profilePhoto);

                                StorageReference storageRef = storage.getReference();
                                final StorageReference pathReference = storageRef.child("users/" + userDetails.getUserName() + "/profile.jpg");

                                //Upload
                                UploadTask uploadTask = pathReference.putBytes(writeData);
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        //if fail display default
                                        Toast.makeText(UpdateProfileActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                                        complete = true;
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(UpdateProfileActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                                        complete = true;
                                    }
                                });
                            }
                        });
        }
    }
}
