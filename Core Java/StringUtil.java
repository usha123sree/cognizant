package com.utils;

public class StringUtil {
    public static String reverse(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return new StringBuilder(text).reverse().toString();
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }
        String cleaned = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(reverse(cleaned));
    }
}