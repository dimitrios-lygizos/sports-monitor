package com.lygizos.sports_monitor.matchodds;

import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.match.Match;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="odds")
public class MatchOdd {
    @Id
    @GeneratedValue
    Integer id;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="match_id")
    Match match;
    @NotNull
    Common.Specifier specifier;
    @NotNull
    double odd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Match getMatch() {
        return match;
    }

    public void setMatch(@NotNull Match match) {
        this.match = match;
    }

    public @NotNull Common.Specifier getSpecifier() {
        return specifier;
    }

    public void setSpecifier(@NotNull Common.Specifier specifier) {
        this.specifier = specifier;
    }

    public @NotNull double getOdd() {
        return odd;
    }

    public void setOdd(@NotNull double odd) {
        this.odd = odd;
    }
}
