package com.lygizos.sports_monitor.model.match;
import static com.lygizos.sports_monitor.Common.*;

import org.springframework.stereotype.Service;

@Service
public class MatchMapper {
    public Match inputRecordToMatch(RecordInput input) {
        Match m = new Match();
        m.setDescription(input.description());
        m.setMatchDate(input.matchDate());
        m.setMatchTime(input.matchTime());
        m.setTeamA(input.teamA());
        m.setTeamB(input.teamB());
        m.setSport(integerToSport(input.sport()));
        return m;
    }

    public RecordOutput MatchToOutputRecord(Match m) {
        return new RecordOutput(
            m.getId(),
            m.getDescription(),
            m.getMatchDate(),
            m.getMatchTime(),
            m.getTeamA(),
            m.getTeamB(),
            sportToInteger(m.getSport())
        );
    }
}
