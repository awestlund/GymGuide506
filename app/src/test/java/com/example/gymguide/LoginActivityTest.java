package com.example.gymguide;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    int VALID_INPUT = 0;
    int INVALID_INPUT = 1;
    int SIGN_IN_FAILED = 3;

    LoginActivity la = new LoginActivity();

    @Test
    public void validateInput() {
        assertEquals(INVALID_INPUT, la.validateInput("", ""));
        assertEquals(VALID_INPUT, la.validateInput("something", "something"));
    }
}