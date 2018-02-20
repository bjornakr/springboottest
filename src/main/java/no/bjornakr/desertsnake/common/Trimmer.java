package no.bjornakr.desertsnake.common;

public class Trimmer {

    public static String apply(String s) {
        if (s != null && s.trim().length() > 0) {
            return s.trim();
        }
        else {
            return null;
        }
    }
}
