package com.lygizos.sports_monitor.model.matchodds;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lygizos.sports_monitor.Common;
import com.lygizos.sports_monitor.model.match.Match;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="odds")
@Getter
@Setter
@NoArgsConstructor
public class MatchOdd
{
    // JPQL
    @Id
    @GeneratedValue
    // JPQL end
    @NonNull  private Integer id;

    // JPQL
    @NotNull
    @ManyToOne
    @JoinColumn( name="match_id" )
    @JsonBackReference
    // JPQL end
    @NonNull private Match match;

    @NotNull // JPQL
    @NonNull private Common.Specifier specifier;

    // JPQL
    @NotNull
    @PositiveOrZero
    // JPQL end
    @NonNull private double odd;

    public void setOdd(double odd) {
        if (odd < 0.0) {
            throw new IllegalArgumentException("Odd must be a Positive/Zero double");
        }
        this.odd = odd;
    }
}
