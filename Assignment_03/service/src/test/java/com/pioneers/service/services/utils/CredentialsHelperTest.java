package com.pioneers.service.services.utils;

import com.pioneers.service.error.exceptions.CredentialsException;
import com.pioneers.service.utils.CredentialsHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsHelperTest {

    // --------------- hashPassword ---------------

    @Test
    void testHashPasswordWhenValidPasswordThenReturnsHashedValue() throws CredentialsException {
        String result = CredentialsHelper.hashPassword("Password@123");
        assertNotNull(result);
    }

    @Test
    void testHashPasswordWhenSameInputThenAlwaysReturnsSameHash() throws CredentialsException {
        String first = CredentialsHelper.hashPassword("Password@123");
        String second = CredentialsHelper.hashPassword("Password@123");
        assertEquals(first, second);
    }

    @Test
    void testHashPasswordWhenDifferentInputsThenReturnsDifferentHashes() throws CredentialsException {
        String first = CredentialsHelper.hashPassword("Password@123");
        String second = CredentialsHelper.hashPassword("OtherPassword@456");
        assertNotEquals(first, second);
    }

    // --------------- verifyPassword ---------------

    @Test
    void testVerifyPasswordWhenCorrectPasswordThenReturnsTrue() throws CredentialsException {
        String hashed = CredentialsHelper.hashPassword("Password@123");
        assertTrue(CredentialsHelper.verifyPassword("Password@123", hashed));
    }

    @Test
    void testVerifyPasswordWhenWrongPasswordThenReturnsFalse() throws CredentialsException {
        String hashed = CredentialsHelper.hashPassword("Password@123");
        assertFalse(CredentialsHelper.verifyPassword("WrongPassword@456", hashed));
    }
}
