package com.lygizos.sports_monitor.match;

public record RecordOutput(
        Integer id,
        String description,
        String matchDate,
        String matchTime,
        String team_a,
        String team_b,
        Integer sport
) {
}
