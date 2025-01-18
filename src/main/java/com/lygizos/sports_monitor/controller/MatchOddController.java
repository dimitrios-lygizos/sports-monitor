package com.lygizos.sports_monitor.controller;

import com.lygizos.sports_monitor.model.matchodds.*;
import com.lygizos.sports_monitor.model.service.SportService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:61112") // For SWAGGER OAD try
public class MatchOddController
{
    private final SportService service;
    public MatchOddController (SportService service)
    {
        this.service = service;
    }

    @PostMapping(value = "/matchodds", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatchOdd(
            @RequestBody MatchOddInputDto input
    ) {
        return service.addOdd(input);
    }

    @GetMapping(path = "/matchodds/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MatchOddOutputDto getMatchOdd(
            @PathVariable Integer id
    ) {
        return service.getOdd(id);
    }

    @GetMapping(path = "/matchodds", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<MatchOddOutputDto> getMatchOdds() {
        return service.retrieveOdds();
    }

    @PutMapping(path = "/matchodds/{matchOddId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MatchOddOutputDto updateMatchOdd(
            @PathVariable Integer matchOddId,
            @RequestBody MatchOddInputDto input
    ) throws BadRequestException {
        return service.updateOdd(matchOddId, input);
    }

    @DeleteMapping({"/matchodds", "/matchodds/{matchOddId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatchOdds(
            @PathVariable(required = false) Integer matchOddId
    ) {
        if (matchOddId != null) service.deleteOdd(matchOddId);
        else service.deleteAllOdds();
    }
}
