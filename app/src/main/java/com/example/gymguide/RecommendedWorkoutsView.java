package com.example.gymguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RecommendedWorkoutsView extends RecyclerView.Adapter<RecommendedWorkoutsView.WorkoutViewHolder> {

    private List<Exercise> mExcersises;
    public Context mContext;

    public RecommendedWorkoutsView(Context context, List<Exercise> excersises){
        this.mExcersises = excersises;
        this.mContext = context;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_workout_list_item, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        Exercise s = mExcersises.get(position);
        Bitmap b = null;
        try {
            URL url = new URL(s.getExerciseVideoURL());
            InputStream in = new BufferedInputStream(url.openStream());
            b = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.workoutImage.setImageBitmap(b);
    }

    @Override
    public int getItemCount() {
        return mExcersises.size();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView workoutImage;
        public WorkoutViewHolder(View itemView){
            super(itemView);
            workoutImage = (ImageView)itemView.findViewById(R.id.workout_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Exercise e = null;
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                e = mExcersises.get(position);
                //           System.out.println("TEST " + e.getExerciseName());
            }

            Intent gotoWorkoutActivityIntent = new Intent(v.getContext(), SingleExerciseActivity.class);
//            Intent gotoWorkoutActivityIntent = new Intent().setClass(v.getContext(), SingleExerciseActivity.class);
            gotoWorkoutActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            gotoWorkoutActivityIntent.putExtra("exercise", e);
            v.getContext().startActivity(gotoWorkoutActivityIntent );
        }
    }
}
