package com.lygizos.sports_monitor.matchodds;

import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.model.match.Match;
import com.lygizos.sports_monitor.model.matchodds.MatchOdd;
import com.lygizos.sports_monitor.model.matchodds.MatchOddMapper;
import com.lygizos.sports_monitor.model.matchodds.MatchOddInputDto;
import com.lygizos.sports_monitor.model.matchodds.MatchOddOutputDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class MatchOddMapperTest {
    final MatchOddMapper mapper = new MatchOddMapper();
    private static final String HOME_INPUT_SPECIFIER = "1";
    private static final String DRAW_INPUT_SPECIFIER = "X";
    private static final String AWAY_INPUT_SPECIFIER = "2";

    @Test
    public void testRequestDtoToMatchOddBeanValid() {
        MatchOddInputDto recordArgument = new MatchOddInputDto(
                99, // match id
                AWAY_INPUT_SPECIFIER, // specifier
                2.45 // odd
        );
        MatchOdd mo = mapper.requestDtoToMatchOddBean(recordArgument);
        assertEquals(recordArgument.odd(), mo.getOdd());
        assertEquals(recordArgument.matchId(), mo.getMatch().getId());
        assertEquals(Common.stringToSpecifier(recordArgument.specifier()), mo.getSpecifier());
    }

    @Test
    public void testRequestDtoToMatchOddBeanValid2() {
        MatchOddInputDto recordArgument = new MatchOddInputDto(
                10, // match id
                DRAW_INPUT_SPECIFIER, // specifier
                2 // odd
        );
        MatchOdd mo = mapper.requestDtoToMatchOddBean(recordArgument);
        assertEquals(recordArgument.odd(), mo.getOdd());
        assertEquals(recordArgument.matchId(), mo.getMatch().getId());
        assertEquals(Common.stringToSpecifier(recordArgument.specifier()), mo.getSpecifier());
    }

    @Test
    public void testRequestDtoToMatchOddBeanNegativeOdd() {
        MatchOddInputDto recordArgument = new MatchOddInputDto(
                10, // match id
                HOME_INPUT_SPECIFIER, // specifier
                -2.1 // odd
        );
        String exceptionMsg = "Odd must be a Positive/Zero double";
        assertThrows(IllegalArgumentException.class, () -> mapper.requestDtoToMatchOddBean(recordArgument), exceptionMsg);
    }

    @ParameterizedTest
    @NullSource
    public void testRequestDtoToMatchOddBeanNullCheck(MatchOddInputDto recordArgument) {
        String exceptionMsg = String.format("input argument of class %s passed in mapper method is null", MatchOddInputDto.class.getSimpleName());
        assertThrows(NullPointerException.class, () -> mapper.requestDtoToMatchOddBean(recordArgument), exceptionMsg);
    }

    @Test
    public void testMatchOddBeanToResponseDtoValid() {
        Match m = new Match();
        m.setId(1);
        m.setMatchDate("23/12/2024");
        m.setMatchTime("12:00");
        MatchOdd mo = new MatchOdd();
        mo.setId(2);
        mo.setMatch(m);
        mo.setOdd(1.99);
        mo.setSpecifier(Common.Specifier.DRAW);

        MatchOddOutputDto mOutput = mapper.matchOddBeanToResponseDto(mo);

        assertEquals(mo.getId(), mOutput.id());
        assertEquals(mo.getMatch().getId(), mOutput.matchId());
        assertEquals(mo.getOdd(), mOutput.odd());
        assertEquals(Common.specifierToString(mo.getSpecifier()), mOutput.specifier());
    }

    @ParameterizedTest
    @NullSource
    public void testNullSourceOnMatchOddToResponseDto(MatchOdd mo) {
        String exceptionMsg = String.format("input argument of class %s passed in mapper method is null", MatchOdd.class.getSimpleName());
        assertThrows(NullPointerException.class, () -> mapper.matchOddBeanToResponseDto(mo), exceptionMsg);
    }
}