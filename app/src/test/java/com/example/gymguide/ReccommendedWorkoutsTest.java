package com.example.gymguide;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReccommendedWorkoutsTest {
    @Test
    public void initReccommendedWorkouts() {
        ReccomendedWorkouts reccomendedWorkouts=new ReccomendedWorkouts();
        assertEquals(null,reccomendedWorkouts.getCategory());
        assertEquals(null,reccomendedWorkouts.getExerciseID());
        assertEquals(null,reccomendedWorkouts.getWorkoutDescription());
        assertEquals(null,reccomendedWorkouts.getWorkoutDifficulty());
        assertEquals(null,reccomendedWorkouts.getWorkoutID());
        assertEquals(null,reccomendedWorkouts.getWorkoutName());
        assertEquals(null,reccomendedWorkouts.getWorkoutPhotoURL());


        System.out.println("Default reccomendedWorkouts methods passed");
        ArrayList<String> list1=new ArrayList<String>();
        list1.add("exerciseIDa");
        list1.add("exerciseIDb");
        list1.add("exerciseIDc");
        ArrayList<String> list2=new ArrayList<String>();
        list2.add("categorya");
        list2.add("categoryb");
        list2.add("categoryc");
        reccomendedWorkouts=new ReccomendedWorkouts("workoutID","workoutName","workoutDescription","workoutPhotoURL","workoutdifficulty", list1, list2);
        assertEquals("categorya",reccomendedWorkouts.getCategory().get(0));
        assertEquals("categoryb",reccomendedWorkouts.getCategory().get(1));
        assertEquals("categoryc",reccomendedWorkouts.getCategory().get(2));
        assertEquals("exerciseIDa",reccomendedWorkouts.getExerciseID().get(0));
        assertEquals("exerciseIDb",reccomendedWorkouts.getExerciseID().get(1));
        assertEquals("exerciseIDc",reccomendedWorkouts.getExerciseID().get(2));
        assertEquals("workoutDescription",reccomendedWorkouts.getWorkoutDescription());
        assertEquals("workoutdifficulty",reccomendedWorkouts.getWorkoutDifficulty());
        assertEquals("workoutID",reccomendedWorkouts.getWorkoutID());
        assertEquals("workoutName",reccomendedWorkouts.getWorkoutName());
        assertEquals("workoutPhotoURL",reccomendedWorkouts.getWorkoutPhotoURL());


        System.out.println("reccomendedWorkouts get methods passed");
        ArrayList<String> list3=new ArrayList<String>();
        list3.add("exerciseIDa123");
        list3.add("exerciseIDb123");
        list3.add("exerciseIDc123");
        ArrayList<String> list4=new ArrayList<String>();
        list4.add("categorya123");
        list4.add("categoryb123");
        list4.add("categoryc123");

        reccomendedWorkouts.setCategory(list4);
        reccomendedWorkouts.setExerciseID(list3);
        reccomendedWorkouts.setWorkoutDescription(reccomendedWorkouts.getWorkoutDescription()+"123");
        reccomendedWorkouts.setWorkoutDifficulty(reccomendedWorkouts.getWorkoutDifficulty()+"123");
        reccomendedWorkouts.setWorkoutID(reccomendedWorkouts.getWorkoutID()+"123");
        reccomendedWorkouts.setWorkoutName(reccomendedWorkouts.getWorkoutName()+"123");
        reccomendedWorkouts.setWorkoutPhotoURL(reccomendedWorkouts.getWorkoutPhotoURL()+"123");

        assertEquals("categorya123",reccomendedWorkouts.getCategory().get(0));
        assertEquals("categoryb123",reccomendedWorkouts.getCategory().get(1));
        assertEquals("categoryc123",reccomendedWorkouts.getCategory().get(2));
        assertEquals("exerciseIDa123",reccomendedWorkouts.getExerciseID().get(0));
        assertEquals("exerciseIDb123",reccomendedWorkouts.getExerciseID().get(1));
        assertEquals("exerciseIDc123",reccomendedWorkouts.getExerciseID().get(2));
        assertEquals("workoutDescription123",reccomendedWorkouts.getWorkoutDescription());
        assertEquals("workoutdifficulty123",reccomendedWorkouts.getWorkoutDifficulty());
        assertEquals("workoutID123",reccomendedWorkouts.getWorkoutID());
        assertEquals("workoutName123",reccomendedWorkouts.getWorkoutName());
        assertEquals("workoutPhotoURL123",reccomendedWorkouts.getWorkoutPhotoURL());

        System.out.println("reccomendedWorkouts set methods passed");

    }
}
