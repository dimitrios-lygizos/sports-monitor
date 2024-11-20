package com.lygizos.sports_monitor.matchodds;

import org.springframework.stereotype.Service;

import static com.lygizos.sports_monitor.Common.*;

@Service
public class MatchOddMapper {
    public MatchOdd requestDtoToMatchOddBean(RecordInput record) {
        MatchOdd mo = new MatchOdd();
        mo.setSpecifier(stringToSpecifier(record.specifier()));
        mo.setOdd(record.odd());
        return mo;
    }

    public RecordOutput matchOddBeanToResponseDto(MatchOdd m) {
        return new RecordOutput(
            m.getId(),
            m.getMatch().getId(),
            specifierToString(m.getSpecifier()),
            m.getOdd()
        );
    }
}
