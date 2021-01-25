package uz.themind.springauthentication.util;

import org.apache.commons.lang3.RandomStringUtils;

public class CustomUtils {

    public static boolean isNullOrEmpty(String string) {
        return (string == null) || (string.trim().isEmpty());
    }

    public String getRandomString(int size){
        return RandomStringUtils.random(size,"absdefghijklmnopqrstuvwxyz1234567890");
    }
}
