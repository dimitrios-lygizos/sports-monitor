package com.lygizos.sports_monitor.match;
import static com.lygizos.sports_monitor.Common.Sport;

import com.lygizos.sports_monitor.matchodds.MatchOdd;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String description;
    @NotNull
    private String matchDate;
    @NotNull
    private String matchTime;
    @NotNull
    private String teamA;
    @NotNull
    private String teamB;
    @NotNull
    private Sport sport;
    @OneToOne(mappedBy = "match")
    private MatchOdd odd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(@NotNull String matchDate) {
        this.matchDate = matchDate;
    }

    public @NotNull String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(@NotNull String matchTime) {
        this.matchTime = matchTime;
    }

    public @NotNull String getTeamA() {
        return teamA;
    }

    public void setTeamA(@NotNull String teamA) {
        this.teamA = teamA;
    }

    public @NotNull String getTeamB() {
        return teamB;
    }

    public void setTeamB(@NotNull String teamB) {
        this.teamB = teamB;
    }
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Match match)) return false;
        return Objects.equals(id, match.id) && Objects.equals(description, match.description) && Objects.equals(matchDate, match.matchDate) && Objects.equals(matchTime, match.matchTime) && Objects.equals(teamA, match.teamA) && Objects.equals(teamB, match.teamB);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}