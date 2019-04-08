package com.example.gymguide;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExerciseTest {
    @Test
    public void initExercise() {
        Exercise exercise=new Exercise();
        assertEquals(null,exercise.getEquipmentID());
        assertEquals(null,exercise.getExerciseDescription());
        assertEquals(null,exercise.getExerciseID());
        assertEquals(null,exercise.getExerciseName());
        assertEquals(null,exercise.getExercisePhotoURL());
        assertEquals(null,exercise.getExerciseVideoURL());


        System.out.println("Default exercise methods passed");

        exercise=new Exercise( "exerciseName","exerciseDescription", "exerciseID", "exerciseVideoURL","exercisePhotoURL","equipmentID");
        assertEquals("exerciseName",exercise.getExerciseName());
        assertEquals("exerciseDescription",exercise.getExerciseDescription());
        assertEquals("exerciseID",exercise.getExerciseID());
        assertEquals("exerciseVideoURL",exercise.getExerciseVideoURL());
        assertEquals("exercisePhotoURL",exercise.getExercisePhotoURL());
        assertEquals("equipmentID",exercise.getEquipmentID());


        System.out.println("exercise get methods passed");

        exercise.setExerciseName(exercise.getExerciseName()+"123");
        exercise.setExerciseDescription(exercise.getExerciseDescription()+"123");
        exercise.setExerciseID(exercise.getExerciseID()+"123");
        exercise.setExerciseVideoURL(exercise.getExerciseVideoURL()+"123");
        exercise.setExercisePhotoURL(exercise.getExercisePhotoURL()+"123");
        exercise.setEquipmentID(exercise.getEquipmentID()+"123");

        assertEquals("exerciseName123",exercise.getExerciseName());
        assertEquals("exerciseDescription123",exercise.getExerciseDescription());
        assertEquals("exerciseID123",exercise.getExerciseID());
        assertEquals("exerciseVideoURL123",exercise.getExerciseVideoURL());
        assertEquals("exercisePhotoURL123",exercise.getExercisePhotoURL());
        assertEquals("equipmentID123",exercise.getEquipmentID());

        System.out.println("exercise set methods passed");

    }

}
