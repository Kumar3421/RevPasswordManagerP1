package com.revpm.tests;

import com.revpm.util.AESUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESUtilTest {

    private static final Logger log = LogManager.getLogger();

    @Test
    void encryptionCycleWorks() {

        log.info("Testing AES encryption/decryption");

        String original = "MySecret123";

        String encrypted = AESUtil.encrypt(original);
        String decrypted = AESUtil.decrypt(encrypted);

        assertEquals(original, decrypted);
        assertNotEquals(original, encrypted);
    }
}