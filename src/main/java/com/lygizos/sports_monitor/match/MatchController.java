package com.lygizos.sports_monitor.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.lygizos.sports_monitor.Common.integerToSport;

@RestController
public class MatchController {
    MatchMapper matchMapper;
    MatchRepository repository;

    public MatchController(
            MatchMapper matchMapper,
            MatchRepository repository
    ) {
        this.matchMapper = matchMapper;
        this.repository = repository;
    }

    @PostMapping("/matches")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatch(
            @RequestBody RecordInput inputMatch
    ) {
        Match rawMatch = matchMapper.inputRecordToMatch(inputMatch);
        Match storedMatch = repository.save(rawMatch);
        return storedMatch.getId().toString();
    }

    @PutMapping("/matches/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RecordOutput updateMatch(@PathVariable(required = true) Integer id, @RequestBody RecordInput inputMatch) {
        // check if raw exists.
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("match with id %d not found", id)
            );
        }

        Match matchToUpdate = repository.getReferenceById(id);
        matchToUpdate.setDescription(inputMatch.description());
        matchToUpdate.setMatchDate(inputMatch.matchDate());
        matchToUpdate.setMatchTime(inputMatch.matchTime());
        matchToUpdate.setTeamA(inputMatch.teamA());
        matchToUpdate.setTeamB(inputMatch.teamB());
        matchToUpdate.setSport(integerToSport(inputMatch.sport()));
        Match updatedRecord = repository.save(matchToUpdate);

        return matchMapper.MatchToOutputRecord(updatedRecord);
    }

    @DeleteMapping({"/matches", "/matches/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatch(@PathVariable(required = false) Integer id) {
        if (id != null) {
            if (repository.existsById(id))
                repository.deleteById(id);
            else
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("match with id %d not found", id)
                );
        } else {
            repository.deleteAll();
        }
    }

    @GetMapping({"/matches", "/matches/{id}"})
    public Collection<RecordOutput> getMatch(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Match m = repository.findById(id).orElseThrow( () -> {return new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("match with id %d not found", id)
            );});
            return Arrays.asList(matchMapper.MatchToOutputRecord(m));
        } else {
            List<Match> matchList = repository.findAll();
            return matchList.stream().map((m) -> {
                return matchMapper.MatchToOutputRecord(m);
            }).toList();
        }
    }
}
