package com.lygizos.sports_monitor.matchodds;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RecordInput(
    @JsonAlias("match_id")
    Integer matchId,
    String specifier,
    double odd
) {
}
