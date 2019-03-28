package com.example.gymguide;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountActivityTest {
    CreateAccountActivity ca = new CreateAccountActivity();

    @Test
    public void validateInput() {
        assertEquals(1, ca.validateInput("", "", ""));
        assertEquals(1, ca.validateInput("someuser@gmail.com", "", "username"));
        assertEquals(1, ca.validateInput("", "somepassword", "username"));
        assertEquals(1, ca.validateInput("someuser@gmail.com", "123456", ""));
        assertEquals(0, ca.validateInput("someuser@gmail.com", "123456", "username"));
    }

    @Test
    public void createAccount() {
        assertEquals(2, ca.createAccount("","", ""));
        assertEquals(2, ca.createAccount("someuser@gmail.com","", "username"));

    }
}