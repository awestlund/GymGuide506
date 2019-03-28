package com.example.gymguide;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountActivityTest {
    CreateAccountActivity ca = new CreateAccountActivity();

    @Test
    public void validateInput() {
        assertEquals(1, ca.validateInput("", "", ""));
    }

    @Test
    public void createAccount() {
        assertEquals(2, ca.createAccount("","", ""));
    }
}