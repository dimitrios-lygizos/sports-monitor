package com.lygizos.sports_monitor.controller;

import com.lygizos.sports_monitor.model.match.*;
import com.lygizos.sports_monitor.model.service.SportService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MatchController {
    private final SportService service;
    public MatchController(SportService service) {
        this.service = service;
    }

    @PostMapping("/matches")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatch (@RequestBody MatchInputDto inputMatch) {
        return service.addMatch(inputMatch);
    }

    @PutMapping("/matches/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MatchOutputDto updateMatch(
            @PathVariable Integer id,
            @RequestBody MatchInputDto inputMatch) {
        return service.modifyMatch(id, inputMatch);
    }

    @DeleteMapping({"/matches", "/matches/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatch(@PathVariable(required = false) Integer id) throws BadRequestException {
        if (id != null) {
            service.deleteMatch(id);
        } else {
            service.deleteMatches();
        }
    }

    @GetMapping("/matches/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MatchOutputDto getMatch(@PathVariable Integer id) {
        return service.findMatchById(id);
    }
    @GetMapping("/matches")
    @ResponseStatus(HttpStatus.OK)
    public Collection<MatchOutputDto> getMatches() {
        return service.retrieveMatches();
    }
}
