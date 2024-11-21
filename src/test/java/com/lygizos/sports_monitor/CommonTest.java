package com.lygizos.sports_monitor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommonTest {
    final String AWAY_SPECIFIER = "2";
    final String HOME_SPECIFIER = "1";
    final String DRAW_SPECIFIER = "X";

    @ParameterizedTest
    @NullSource
    void integerToSportNullCheck(Integer i) {
        String exceptionMsg = "Sport argument provided is empty, please try again.";
        assertThrows(IllegalArgumentException.class, () -> Common.integerToSport(i), exceptionMsg);
    }

    @ParameterizedTest
    @ValueSource (ints = {0,1,2})
    void integerToSport(int i) {
        Common.Sport[] values = Common.Sport.values();
        assertEquals(values[i], Common.integerToSport(i));
    }

    @Test
    void integerToSportBiggerThan2() {
        assertEquals(Common.Sport.UNDEFINED, Common.integerToSport(9));
    }

    @Test
    void sportToInteger() {
        assertEquals(0, Common.sportToInteger(Common.Sport.UNDEFINED));
        assertEquals(1, Common.sportToInteger(Common.Sport.FOOTBALL));
        assertEquals(2, Common.sportToInteger(Common.Sport.BASKETBALL));
    }

    @Test
    void stringToSpecifier() {
        assertEquals(Common.Specifier.HOME, Common.stringToSpecifier(HOME_SPECIFIER));
        assertEquals(Common.Specifier.AWAY, Common.stringToSpecifier(AWAY_SPECIFIER));
        assertEquals(Common.Specifier.DRAW, Common.stringToSpecifier(DRAW_SPECIFIER));
        assertEquals(Common.Specifier.UNDEFINED, Common.stringToSpecifier("Other"));
    }

    @Test
    void specifierToString() {
        assertEquals(DRAW_SPECIFIER, Common.specifierToString(Common.Specifier.DRAW));
        assertEquals(HOME_SPECIFIER, Common.specifierToString(Common.Specifier.HOME));
        assertEquals(AWAY_SPECIFIER, Common.specifierToString(Common.Specifier.AWAY));
    }
}