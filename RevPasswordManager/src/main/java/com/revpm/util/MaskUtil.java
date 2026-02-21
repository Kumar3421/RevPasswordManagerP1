package com.revpm.util;

public class MaskUtil {

    public static String mask(String s) {
        return "*".repeat(s.length() - 2) + s.substring(s.length() - 2);
    }
}