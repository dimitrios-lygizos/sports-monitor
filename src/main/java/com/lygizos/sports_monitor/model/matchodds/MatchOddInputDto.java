package com.lygizos.sports_monitor.model.matchodds;

import com.fasterxml.jackson.annotation.JsonAlias;

public record MatchOddInputDto(
    @JsonAlias("match_id")
    Integer matchId,
    String specifier,
    double odd
) {
}
