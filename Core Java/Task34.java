package com.greetings;

import com.utils.StringUtil;

public class Task34 {
    public static void main(String[] args) {
        String greeting = "Hello, Java Modules!";
        String word = "level";

        System.out.println("Original string: " + greeting);
        System.out.println("Reversed string: " + StringUtil.reverse(greeting));

        System.out.println("\nIs '" + word + "' a palindrome? " + StringUtil.isPalindrome(word));
        System.out.println("Is '" + greeting + "' a palindrome? " + StringUtil.isPalindrome(greeting));
    }
}
