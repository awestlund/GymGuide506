package com.example.gymguide;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WorkoutHistoryTest {
    @Test
    public void initWorkoutHistory() {
        WorkoutHistory workoutHistory=new WorkoutHistory();
        assertEquals(null,workoutHistory.getExerciseID());
        assertEquals(null,workoutHistory.getHistoryID());
        assertEquals(null,workoutHistory.getUserID());
        assertEquals(null,workoutHistory.getWorkoutDate());


        System.out.println("Default WorkoutHistory methods passed");
//        Date now = new java.util.Date();
//        Timestamp current = new java.sql.Timestamp(now.getTime());
//        System.out.print(current);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse("2019-04-08 17:16:57.092");
            ts = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        ArrayList<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");


        workoutHistory=new WorkoutHistory( "historyID", ts, list, "userID");
        assertEquals("historyID",workoutHistory.getHistoryID());
        assertEquals("2019-04-08 17:16:57.092",workoutHistory.getWorkoutDate().toString());
        assertEquals("a",workoutHistory.getExerciseID().get(0));
        assertEquals("b",workoutHistory.getExerciseID().get(1));
        assertEquals("c",workoutHistory.getExerciseID().get(2));
        assertEquals("userID",workoutHistory.getUserID());


        System.out.println("workoutHistory get methods passed");
        ArrayList<String> list2=new ArrayList<String>();
        list2.add("a123");
        list2.add("b123");
        list2.add("c123");
        Timestamp ts2 = new Timestamp(System.currentTimeMillis());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse("2018-03-07 16:15:56.012");
            ts2 = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }



        workoutHistory.setExerciseID(list2);
        workoutHistory.setHistoryID(workoutHistory.getHistoryID()+"123");
        workoutHistory.setUserID(workoutHistory.getUserID()+"123");
        workoutHistory.setWorkoutDate(ts2);

        assertEquals("historyID123",workoutHistory.getHistoryID());
        assertEquals("2018-03-07 16:15:56.012",workoutHistory.getWorkoutDate().toString());
        assertEquals("a123",workoutHistory.getExerciseID().get(0));
        assertEquals("b123",workoutHistory.getExerciseID().get(1));
        assertEquals("c123",workoutHistory.getExerciseID().get(2));
        assertEquals("userID123",workoutHistory.getUserID());

        System.out.println("workoutHistory set methods passed");

    }
}
