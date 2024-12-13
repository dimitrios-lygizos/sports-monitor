package com.lygizos.sports_monitor.model.matchodds;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.model.match.Match;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="odds")
public class MatchOdd {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @ManyToOne
    @JoinColumn( name="match_id" )
    @JsonBackReference
    private Match match;
    @NotNull
    private Common.Specifier specifier;
    @NotNull
    @PositiveOrZero
    private double odd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Match getMatch() {
        return match;
    }

    public void setMatch(@NotNull Match matchParent) {
        this.match = matchParent;
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
        if (odd < 0.0) {
            throw new IllegalArgumentException("Odd must be a Positive/Zero double");
        }
        this.odd = odd;
    }
}
