package com.revpm.util;

public class InputValidator {

    public static boolean strongPassword(String p) {
        return p.length() >= 8 &&
                p.matches(".*[A-Z].*") &&
                p.matches(".*[0-9].*") &&
                p.matches(".*[@#$%!].*");
    }

    public static boolean validEmail(String e) {
        return e.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
}