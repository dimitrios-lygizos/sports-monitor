package com.lygizos.sports_monitor;

public class Common {

    public enum Sport {
        UNDEFINED,
        FOOTBALL,
        BASKETBALL
    }

    public enum Specifier {
        UNDEFINED,
        HOME,
        DRAW,
        AWAY
    }

    public static Sport integerToSport(Integer i) {
        if (i == null) {
            throw new IllegalArgumentException("Sport argument provided is empty, please try again.");
        }
        return switch (i) {
            case 1 -> Sport.FOOTBALL;
            case 2 -> Sport.BASKETBALL;
            default -> Sport.UNDEFINED;
        };
    }

    public static Integer sportToInteger(Sport s) {
        return s.ordinal();
    }

    public static Specifier stringToSpecifier(String i) {
        return switch (i) {
            case "1" -> Specifier.HOME;
            case "X" -> Specifier.DRAW;
            case "2" -> Specifier.AWAY;
            default -> Specifier.UNDEFINED;
        };
    }

    public static String specifierToString(Specifier sp) {
        return switch(sp) {
            case HOME -> "1";
            case DRAW -> "X";
            case AWAY -> "2";
            default -> "N/A";
        };
    }
}