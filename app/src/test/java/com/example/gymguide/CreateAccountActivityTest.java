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
    }

    @Test
    public void createAccount() {
        assertEquals(PASSWORD_SHORT, ca.createAccount("","", ""));
    }
}