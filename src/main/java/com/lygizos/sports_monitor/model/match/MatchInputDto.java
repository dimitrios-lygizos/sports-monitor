package com.lygizos.sports_monitor.model.match;

import com.fasterxml.jackson.annotation.JsonAlias;

public record MatchInputDto(
    String description,
    @JsonAlias("match_date")
    String matchDate,
    @JsonAlias("match_time")
    String matchTime,
    @JsonAlias("team_a")
    String teamA,
    @JsonAlias("team_b")
    String teamB,
    Integer sport
) {
}
