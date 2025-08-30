package com.example.papasoftclient.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validate {
    public static boolean number(String str){
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, str);
    }

    public static boolean email(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    public static boolean noControl(String num){
        String regex = "^[A-Z]?\\d{8}$";
        return Pattern.matches(regex, num);
    }

    public static boolean name(String name){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{3,50}$";
        return Pattern.matches(regex, name);
    }

    public static boolean lastName(String lastName){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{3,50}$";
        return Pattern.matches(regex, lastName);
    }

    public static boolean phone(String phone){
        String regex = "^\\d{10}$";
        return Pattern.matches(regex, phone);
    }

    public static boolean date(Date date){
        return date.before(Date.valueOf(LocalDate.now()));
    }

    public static boolean word(String word){
        String regex = "^[a-zA-Z0-9 ]{3,50}$";
        return Pattern.matches(regex, word);
    }
}
