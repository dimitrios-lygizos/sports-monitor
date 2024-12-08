package com.lygizos.sports_monitor.matchodds;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RecordOutput(
    Integer id,
    @JsonAlias("match_id")
    Integer matchId,
    String specifier,
    double odd
) {
}
