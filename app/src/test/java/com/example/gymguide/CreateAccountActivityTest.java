package com.example.gymguide;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class CreateAccountActivityTest {
    CreateAccountActivity ca = new CreateAccountActivity();

    int VALID_INPUT = 0;
    int INVALID_INPUT = 1;
    int PASSWORD_SHORT = 2;
    int CREATE_ACCOUNT_FAILED = 3;


    @Test
    public void validateInput() {
        assertEquals(INVALID_INPUT, ca.validateInput("", "", ""));
        assertEquals(INVALID_INPUT, ca.validateInput("someuser@gmail.com", "", "username"));
        assertEquals(INVALID_INPUT, ca.validateInput("", "somepassword", "username"));
        assertEquals(INVALID_INPUT, ca.validateInput("someuser@gmail.com", "123456", ""));
        assertEquals(VALID_INPUT, ca.validateInput("someuser@gmail.com", "123456", "username"));
    }

    @Test
    public void createAccount() {
        assertEquals(PASSWORD_SHORT, ca.createAccount("","", ""));
        assertEquals(PASSWORD_SHORT, ca.createAccount("someuser@gmail.com","", "username"));

    }
}