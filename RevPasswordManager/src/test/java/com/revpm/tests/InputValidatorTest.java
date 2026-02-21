package com.revpm.tests;

import com.revpm.util.InputValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void strongPasswordValidation() {

        assertTrue(InputValidator.strongPassword("Strong@123"));
        assertFalse(InputValidator.strongPassword("weak"));
    }

    @Test
    void emailValidation() {

        assertTrue(InputValidator.validEmail("test@gmail.com"));
        assertFalse(InputValidator.validEmail("bad-email"));
    }
}