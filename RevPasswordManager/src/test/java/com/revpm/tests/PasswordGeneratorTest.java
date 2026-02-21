package com.revpm.tests;

import com.revpm.util.PasswordGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void generatesCorrectLength() {
        assertEquals(12, PasswordGenerator.generate(12).length());
    }

    @Test
    void neverReturnsNull() {
        assertNotNull(PasswordGenerator.generate(8));
    }
}