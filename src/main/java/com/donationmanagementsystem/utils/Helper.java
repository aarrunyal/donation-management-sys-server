package com.donationmanagementsystem.utils;

import java.util.Random;

public class Helper {
    
    public static String getRandomToken(int tokenLength){
        tokenLength = tokenLength<=0?256:tokenLength;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < tokenLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
