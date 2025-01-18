package com.lygizos.sports_monitor.controller;

import com.lygizos.sports_monitor.model.match.*;
import com.lygizos.sports_monitor.model.service.SportService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:61112") // For SWAGGER OAD try
public class MatchController {
    private final SportService service;
    public MatchController(SportService service) {
        this.service = service;
    }

    @PostMapping(path = "/matches", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatch (@RequestBody MatchInputDto inputMatch) {
        return service.addMatch(inputMatch);
    }

    @PutMapping(path = "/matches/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(path = "/matches/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MatchOutputDto getMatch(@PathVariable Integer id) {
        return service.findMatchById(id);
    }

    @GetMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<MatchOutputDto> getMatches() {
        return service.retrieveMatches();
    }
}
