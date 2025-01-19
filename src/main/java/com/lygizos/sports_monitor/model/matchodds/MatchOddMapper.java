package com.lygizos.sports_monitor.model.matchodds;

import com.lygizos.sports_monitor.model.match.Match;
import org.springframework.stereotype.Service;

import static com.lygizos.sports_monitor.Common.*;

@Service
public class MatchOddMapper {
    public MatchOdd requestDtoToMatchOddBean(MatchOddInputDto record) {
        if (record == null) {
            throw new NullPointerException(String.format("input argument of class %s passed in mapper method is null", MatchOddInputDto.class.getSimpleName()));
        }
        MatchOdd mo = new MatchOdd();
        mo.setSpecifier(stringToSpecifier(record.specifier()));
        mo.setOdd(record.odd());
        Match m = new Match();
        m.setId(record.matchId());
        mo.setMatch(m);
        return mo;
    }

    public MatchOddOutputDto matchOddBeanToResponseDto(MatchOdd m) {
        if (m == null) {
            throw new NullPointerException(String.format("input argument of class %s passed in mapper method is null", MatchOdd.class.getSimpleName()));
        }
        return new MatchOddOutputDto(
            m.getId(),
            m.getMatch().getId(),
            specifierToString(m.getSpecifier()),
            m.getOdd()
        );
    }
}
