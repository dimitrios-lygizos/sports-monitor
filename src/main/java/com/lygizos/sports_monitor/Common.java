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
            case "HOME" -> Specifier.HOME;
            case "X" -> Specifier.DRAW;
            case "AWAY" -> Specifier.AWAY;
            default -> Specifier.UNDEFINED;
        };
    }

    public static String specifierToString(Specifier sp) {
        return switch(sp) {
            case HOME -> "HOME";
            case DRAW -> "X";
            case AWAY -> "AWAY";
            default -> "N/A";
        };
    }
}