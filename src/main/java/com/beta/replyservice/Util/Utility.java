package com.beta.replyservice.Util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static boolean matchesRegex(String message) {
        String regex = "[a-z-0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.matches();
    }
    public static String returnEncryptedString(String message) {
        String valueHash = Hashing.md5().hashString(message, StandardCharsets.UTF_8).toString();
        return valueHash;
    }

    public static String reverseString(String message) {
        StringBuilder reverse = new StringBuilder();
        reverse.append(message);
        reverse.reverse();
        return reverse.toString();
    }
}
