package com.example.gymguide;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymguide.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends Fragment{

    Button logout;
    FirebaseFirestore db;
    FirebaseAuth auth;

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

        logout =(Button)rootView.findViewById(R.id.btnLogout);

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
