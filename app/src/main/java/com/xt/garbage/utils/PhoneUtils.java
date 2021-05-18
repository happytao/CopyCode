package com.xt.garbage.utils;

import java.util.regex.Pattern;

/**
 * @author:DIY
 * @date: 2021/3/26
 */
public class PhoneUtils {
    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile(regex);
        return p.matches(regex,input);
    }
}
