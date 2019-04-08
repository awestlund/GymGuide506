package com.example.gymguide;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserTest {


    @Test
    public void initUser() {
        User user =new User();
        assertEquals(null,user.getUserEmail());
        assertEquals(null,user.getUserProfilePictureURL());
        assertEquals(null,user.getWorkoutDifficulty());
        assertEquals(null,user.getUserWorkoutGoal());
        assertEquals(null,user.getUserName());
        assertEquals(null,user.getWorkoutCategory());
        assertEquals(null,user.getUserID());


        System.out.println("Default User methods passed");

        ArrayList<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        user=new User( "userID", "userName", "userEmail", "userWorkoutGoal", "workoutDifficulty","userProfilePictureURL", list);
        assertEquals("userID",user.getUserID());
        assertEquals("userName",user.getUserName());
        assertEquals("userEmail",user.getUserEmail());
        assertEquals("userWorkoutGoal",user.getUserWorkoutGoal());
        assertEquals("workoutDifficulty",user.getWorkoutDifficulty());
        assertEquals("userProfilePictureURL",user.getUserProfilePictureURL());
        assertEquals("a",user.getWorkoutCategory().get(0));
        assertEquals("b",user.getWorkoutCategory().get(1));
        assertEquals("c",user.getWorkoutCategory().get(2));

        System.out.println("User get methods passed");

        user.setUserID(user.getUserID()+"123");
        user.setUserName(user.getUserName()+"123");
        user.setUserEmail(user.getUserEmail()+"123");
        user.setUserWorkoutGoal(user.getUserWorkoutGoal()+"123");
        user.setWorkoutDifficulty(user.getWorkoutDifficulty()+"123");
        user.setUserProfilePictureURL(user.getUserProfilePictureURL()+"123");
        ArrayList<String> list2=new ArrayList<String>();
        list2.add("a123");
        list2.add("b123");
        list2.add("c123");
        user.setWorkoutCategory(list2);

        assertEquals("userID123",user.getUserID());
        assertEquals("userName123",user.getUserName());
        assertEquals("userEmail123",user.getUserEmail());
        assertEquals("userWorkoutGoal123",user.getUserWorkoutGoal());
        assertEquals("workoutDifficulty123",user.getWorkoutDifficulty());
        assertEquals("userProfilePictureURL123",user.getUserProfilePictureURL());
        assertEquals("a123",user.getWorkoutCategory().get(0));
        assertEquals("b123",user.getWorkoutCategory().get(1));
        assertEquals("c123",user.getWorkoutCategory().get(2));

        System.out.println("User set methods passed");

    }
}
