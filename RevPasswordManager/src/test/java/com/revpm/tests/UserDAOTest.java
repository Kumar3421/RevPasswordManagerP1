package com.revpm.tests;

import com.revpm.dao.UserDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void userInsertAndFetchWorks() throws Exception {

        String email = "junit@test.com";

        UserDAO.register("JUnitUser", email, "hash");

        assertTrue(UserDAO.find(email).next());
    }
}