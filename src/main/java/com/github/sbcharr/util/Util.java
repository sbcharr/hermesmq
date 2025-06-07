package com.github.sbcharr.util;

public class Util {
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            String str = (String) obj;
            return str.isEmpty();
        }

        return false;
    }
}
