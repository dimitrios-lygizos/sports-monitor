package com.lygizos.sports_monitor.controller;

import com.lygizos.sports_monitor.model.matchodds.*;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static com.lygizos.sports_monitor.Common.stringToSpecifier;

@RestController
public class MatchOddController {
    private final MatchOddMapper oddMapper;
    private final MatchOddRepository repository;

    public MatchOddController(
            MatchOddMapper oddMapper,
            MatchOddRepository repository
    ) {
        this.oddMapper = oddMapper;
        this.repository = repository;
    }

    @PostMapping("/matchodds")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMatchOdd(
            @RequestBody RecordInput input
    ) {
        MatchOdd beanBeforeSave = oddMapper.requestDtoToMatchOddBean(input);
        MatchOdd beanAfterSave = repository.save(beanBeforeSave);
        return beanAfterSave.getId().toString();
    }

    @GetMapping({"/matchodds", "/matchodds/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<RecordOutput> getMatchOdds(
            @PathVariable(required = false) Integer id
    ) {
        if (id != null) {
            List<RecordOutput> recordOutput = new ArrayList<>(1);
            recordOutput.add(
                oddMapper.matchOddBeanToResponseDto( repository.findById(id)
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", id)))
                )
            );
            return recordOutput;
        } else {
            List<MatchOdd> allOdds = repository.findAll();
            return allOdds.stream().map(oddMapper::matchOddBeanToResponseDto).toList();
        }
    }

    @PutMapping("/matchodds/{matchOddId}")
    @ResponseStatus(HttpStatus.OK)
    public RecordOutput updateMatchOdd(
            @PathVariable Integer matchOddId,
            @RequestBody RecordInput input
    ) throws BadRequestException {
        if (matchOddId == null) {
            throw new BadRequestException("PathVariable matchOddId not provided. Please check again");
        }
        if (!repository.existsById(matchOddId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", matchOddId));
        }
        MatchOdd matchOddToUpdate = repository.getReferenceById(matchOddId);
        matchOddToUpdate.setOdd(input.odd());
        matchOddToUpdate.setSpecifier(stringToSpecifier(input.specifier()));
        MatchOdd matchOddAfterUpdate = repository.save(matchOddToUpdate);
        return oddMapper.matchOddBeanToResponseDto(matchOddAfterUpdate);
    }

    @DeleteMapping({"/matchodds", "/matchodds/{matchOddId}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatchOdds(
            @PathVariable(required = false) Integer matchOddId
    ) {
        if (matchOddId != null) {
            if (repository.existsById(matchOddId)) {
                repository.deleteById(matchOddId);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", matchOddId));
            }
        } else {
            repository.deleteAll();
        }

    }
}
