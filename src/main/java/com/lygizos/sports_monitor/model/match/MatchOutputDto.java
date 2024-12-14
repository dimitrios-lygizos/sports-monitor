package com.lygizos.sports_monitor.model.match;

public record MatchOutputDto(
        Integer id,
        String description,
        String matchDate,
        String matchTime,
        String team_a,
        String team_b,
        Integer sport
) {
}
