package com.lygizos.sports_monitor.controller;

import com.lygizos.sports_monitor.model.matchodds.*;
import com.lygizos.sports_monitor.model.service.SportService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static com.lygizos.sports_monitor.Common.stringToSpecifier;

@RestController
public class MatchOddController
{
    private final SportService service;
    public MatchOddController (SportService service)
    {
        this.service = service;
    }

    @PostMapping("/matchodds")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatchOdd(
            @RequestBody MatchOddInputDto input
    ) {
        return service.addOdd(input);
    }

    @GetMapping("/matchodds/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MatchOddOutputDto getMatchOdd(
            @PathVariable(required = true) Integer id
    ) {
        return service.getOdd(id);
    }

    @GetMapping("/matchodds")
    @ResponseStatus(HttpStatus.OK)
    public Collection<MatchOddOutputDto> getMatchOdds() {
        return
    }

    @PutMapping("/matchodds/{matchOddId}")
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
