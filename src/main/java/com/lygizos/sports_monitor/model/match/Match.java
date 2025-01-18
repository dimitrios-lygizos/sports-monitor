package com.lygizos.sports_monitor.model.match;

import static com.lygizos.sports_monitor.Common.Sport;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lygizos.sports_monitor.model.matchodds.MatchOdd;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="matches")
@NoArgsConstructor
public @Data class Match
{
    // JPQL
    @Id
    @GeneratedValue
    // JPQL end
    @NonNull private Integer id;

    @NotNull
    @NonNull private String description;

    @NotNull
    @NonNull private String matchDate;

    @NotNull
    @NonNull private String matchTime;

    @NotNull
    @NonNull private String teamA;

    @NotNull
    @NonNull private String teamB;

    @NotNull
    @NonNull private Sport sport;

    // JPQL
    @OneToMany(
            mappedBy = "match",
            cascade = {CascadeType.ALL}
    )
    @JsonManagedReference
    // JPQL end
    @NonNull private List<MatchOdd> oddList = new ArrayList<>();
}
