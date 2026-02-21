package com.revpm.tests;

import com.revpm.util.EncryptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {

    private static final Logger log = LogManager.getLogger();

    @Test
    void hashAndVerifyWorks() {

        log.info("Testing password hashing");

        String password = "Strong@123";

        String hashed = EncryptionUtil.hash(password);

        assertTrue(EncryptionUtil.verify(password, hashed));
        assertFalse(EncryptionUtil.verify("wrong", hashed));
    }
}