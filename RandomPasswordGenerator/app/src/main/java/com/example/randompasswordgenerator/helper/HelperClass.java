package com.example.randompasswordgenerator.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HelperClass {

    static String all;
    static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    static final String NUMBERS = "0123456789";
    static final String SYMBOLS = "[]{}()!@#$%^&*_-'";
    static String password = "";
    static final int count = UPPERCASE.length() + LOWERCASE.length() + NUMBERS.length() + SYMBOLS.length();
    Random random = new Random();

    public String generate(boolean upper,boolean lower,boolean number,boolean symbol,int length,String custom){
        all = "";
        password = "";
        if (custom.length() > 0 && custom.length() < length)
            password = custom;
        if (upper) {
            all += UPPERCASE;
            password += UPPERCASE.charAt(random.nextInt(UPPERCASE.length()));
        }
        if (lower) {
            all += LOWERCASE;
            password += LOWERCASE.charAt(random.nextInt(LOWERCASE.length()));
        }
        if (number) {
            all += NUMBERS;
            password += NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }
        if (symbol) {
            all += SYMBOLS;
            password += SYMBOLS.charAt(random.nextInt(SYMBOLS.length()));
        }

        length -= password.length();

        for (int i = 0; i < length; i++) {
            password += all.charAt(random.nextInt(all.length()));
        }

        String[] temp = password.split("");
        List<String> list = new ArrayList<String>();
        for (String t : temp) {
            list.add(t);
        }
        Collections.shuffle(list);
        Collections.shuffle(list);
        Collections.shuffle(list);
        Collections.shuffle(list);
        password = String.join("", list);
        return password;
    }

    public void copyPassword(Context context,String password){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("password", password);
        clipboard.setPrimaryClip(clip);
    }
}
