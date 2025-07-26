package com.korruptengu.gymcheckinsystem.utils;

public class StringUtils {

    public static String capitalizeFirstLetter(String str) {
        char firstLetter = str.charAt(0);
        char capitalFirstLetter = Character.toUpperCase(firstLetter);
        return str.replace(str.charAt(0), capitalFirstLetter);
    }
}
