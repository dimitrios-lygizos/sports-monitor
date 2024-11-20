package com.lygizos.sports_monitor.matchodds;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MatchOddController {
    MatchOddMapper oddMapper;
    MatchOddRepository repository;

    public MatchOddController(
            MatchOddMapper oddMapper,
            MatchOddRepository repository
    ) {
        this.oddMapper = oddMapper;
        this.repository = repository;
    }

    @PostMapping("/matchodds")
    public String addMatchOdd(
            @RequestBody RecordInput input
    ) {
        return "";
    }

    @GetMapping({"/matchodds", "/matchodds/{id}"})
    public Collection<RecordOutput> getMatchOdds(
            @PathVariable(required = false) Integer matchOddId
    ) {
        return null;
    }

    @PutMapping("/matchodds/{id}")
    public RecordOutput updateMatchOdd(
            @PathVariable Integer matchOddId,
            @RequestBody RecordInput input
    ) {
        return null;
    }

    @DeleteMapping({"/matchodds", "/matchodds/{id}"})
    public void deleteMatchOdds(
            @PathVariable(required = false) Integer matchOddId
    ) {}
}
