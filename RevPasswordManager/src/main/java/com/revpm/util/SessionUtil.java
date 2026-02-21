package com.revpm.util;

public class SessionUtil {

    private static long expiry;

    public static void refresh() {
        expiry = System.currentTimeMillis() + 600000;
    }

    public static boolean isExpired() {
        return System.currentTimeMillis() > expiry;
    }
}