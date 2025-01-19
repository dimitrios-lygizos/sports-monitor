package com.lygizos.sports_monitor.match;

import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.model.match.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchMapperTest {

    private final MatchMapper mapper = new MatchMapper();


    @Test
    void inputRecordToMatch() {
        MatchInputDto matchDto = new MatchInputDto("OMADA1-OMADA2", "Match-Date", "Match-Time", "OMADA1", "OMADA2", 1);
        Match output = mapper.inputRecordToMatch(matchDto);
        assertEquals (matchDto.description(), output.getDescription());
        assertEquals (matchDto.matchDate(), output.getMatchDate());
        assertEquals (matchDto.matchTime(), output.getMatchTime());
        assertEquals (matchDto.teamA(), output.getTeamA());
        assertEquals (matchDto.teamB(), output.getTeamB());
    }

    @Test
    void matchToOutputRecord() {
        Match match = new Match();
        match.setDescription("OMADA1-OMADA2");
        match.setMatchDate("Match-Date");
        match.setMatchTime("Match-Time");
        match.setTeamA("OMADA1");
        match.setTeamB("OMADA2");
        match.setSport(Common.Sport.BASKETBALL);
        MatchOutputDto output = mapper.MatchToOutputRecord(match);
        assertEquals (match.getDescription(), output.description());
        assertEquals (match.getMatchDate(), output.matchDate());
        assertEquals (match.getMatchTime(), output.matchTime());
        assertEquals (match.getTeamA(), output.team_a());
        assertEquals (match.getTeamB(), output.team_b());
    }

    @ParameterizedTest
    @NullSource
    void checkNullOrEmptyDtoToMatch(MatchInputDto dto) {
        String exceptionMsg = String.format("input argument of class %s passed in mapper method is null", MatchInputDto.class.getSimpleName());
        assertThrows(NullPointerException.class, () -> mapper.inputRecordToMatch(dto), exceptionMsg);
    }

    @ParameterizedTest
    @NullSource
    void checkNullOrEmptyMatchToDto(Match m) {
        String exceptionMsg = String.format("input argument of class %s passed in mapper method is null", Match.class.getSimpleName());
        assertThrows(NullPointerException.class, () -> mapper.MatchToOutputRecord(m), exceptionMsg);
    }
}