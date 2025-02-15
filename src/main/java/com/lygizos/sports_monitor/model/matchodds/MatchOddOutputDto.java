package com.lygizos.sports_monitor.model.matchodds;

import com.fasterxml.jackson.annotation.JsonAlias;

public record MatchOddOutputDto(
    Integer id,
    @JsonAlias("match_id")
    Integer matchId,
    String specifier,
    double odd
) {
}
