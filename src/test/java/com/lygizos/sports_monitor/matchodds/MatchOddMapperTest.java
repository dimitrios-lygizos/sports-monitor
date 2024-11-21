package com.lygizos.sports_monitor.matchodds;

import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.match.Match;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class MatchOddMapperTest {
    final MatchOddMapper mapper = new MatchOddMapper();
    final String AWAY_SPECIFIER = "2";
    final String HOME_SPECIFIER = "1";
    final String DRAW_SPECIFIER = "X";

    @Test
    public void testRequestDtoToMatchOddBeanValid() {
        RecordInput recordArgument = new RecordInput(
                99, // match id
                AWAY_SPECIFIER, // specifier
                2.45 // odd
        );
        MatchOdd mo = mapper.requestDtoToMatchOddBean(recordArgument);
        assertEquals(mo.getOdd(), recordArgument.odd());
        assertEquals(mo.getMatch().getId(), recordArgument.matchId());
        assertEquals(mo.getSpecifier(), Common.stringToSpecifier(AWAY_SPECIFIER));
    }

    @Test
    public void testRequestDtoToMatchOddBeanValid2() {
        RecordInput recordArgument = new RecordInput(
                10, // match id
                DRAW_SPECIFIER, // specifier
                2 // odd
        );
        MatchOdd mo = mapper.requestDtoToMatchOddBean(recordArgument);
        assertEquals(mo.getOdd(), recordArgument.odd());
        assertEquals(mo.getMatch().getId(), recordArgument.matchId());
        assertEquals(mo.getSpecifier(), Common.stringToSpecifier(DRAW_SPECIFIER));
    }

    @Test
    public void testRequestDtoToMatchOddBeanNegativeOdd() {
        RecordInput recordArgument = new RecordInput(
                10, // match id
                DRAW_SPECIFIER, // specifier
                -2.1 // odd
        );
        String exceptionMsg = "Odd must be a Positive/Zero double";
        assertThrows(IllegalArgumentException.class, () -> mapper.requestDtoToMatchOddBean(recordArgument), exceptionMsg);
    }

    @ParameterizedTest
    @NullSource
    public void testRequestDtoToMatchOddBeanNullCheck(RecordInput recordArgument) {
        String exceptionMsg = "Input provided is null, please provide a valid input source";
        assertThrows(IllegalArgumentException.class, () -> mapper.requestDtoToMatchOddBean(recordArgument), exceptionMsg);
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
        mo.setSpecifier(Common.stringToSpecifier(HOME_SPECIFIER));

        RecordOutput mOutput = mapper.matchOddBeanToResponseDto(mo);

        assertEquals(mOutput.id(), mo.getId());
        assertEquals(mOutput.matchId(), mo.getMatch().getId());
        assertEquals(mOutput.odd(), mo.getOdd());
        assertEquals(mOutput.specifier(), HOME_SPECIFIER);
    }

}