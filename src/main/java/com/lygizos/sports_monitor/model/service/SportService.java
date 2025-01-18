package com.lygizos.sports_monitor.model.service;

import com.lygizos.sports_monitor.model.match.*;
import com.lygizos.sports_monitor.model.matchodds.*;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static com.lygizos.sports_monitor.Common.integerToSport;
import static com.lygizos.sports_monitor.Common.stringToSpecifier;

@Service
public class SportService {
    MatchMapper matchMapper;
    MatchOddMapper matchOddMapper;
    MatchRepository matchRepository;
    MatchOddRepository matchOddRepository;

    public SportService(
            MatchMapper matchMapper,
            MatchOddMapper matchOddMapper,
            MatchRepository matchRepository,
            MatchOddRepository matchOddRepository
    ) {
        this.matchMapper = matchMapper;
        this.matchOddMapper = matchOddMapper;
        this.matchRepository = matchRepository;
        this.matchOddRepository = matchOddRepository;
    }

    /** MATCH OPERATIONS **/

    /**
     * Add new Match object in database
     * @param matchInputDto
     * @return ID of the new Match object
     */
    public String addMatch(MatchInputDto matchInputDto) {
        Match rawMatch = matchMapper.inputRecordToMatch(matchInputDto);
        return matchRepository.save(rawMatch).getId().toString();
    }

    /**
     * Replace the contents of a match object with the new given ones
     * @param matchId
     * @param matchInputDto
     * @return
     */
    public MatchOutputDto modifyMatch(Integer matchId, MatchInputDto matchInputDto) {
        // check if raw exists.
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("match with id %d not found", matchId)
            );
        }

        Match matchToUpdate = matchRepository.getReferenceById(matchId);
        matchToUpdate.setDescription(matchInputDto.description());
        matchToUpdate.setMatchDate(matchInputDto.matchDate());
        matchToUpdate.setMatchTime(matchInputDto.matchTime());
        matchToUpdate.setTeamA(matchInputDto.teamA());
        matchToUpdate.setTeamB(matchInputDto.teamB());
        matchToUpdate.setSport(integerToSport(matchInputDto.sport()));
        return matchMapper.MatchToOutputRecord(matchRepository.save(matchToUpdate));
    }

    /**
     * Delete all Match objects from the database
     */
    public void deleteMatches() {
        matchRepository.deleteAll();
    }

    /**
     * Delete the Match object, based on the given input
     * @param matchId, ID of the Match object to delete
     */
    public void deleteMatch(Integer matchId) throws BadRequestException {
        if (matchId == null) throw new BadRequestException("Match Id passed to delete is null.");
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("match with id %d not found", matchId)
            );
        }
        matchRepository.deleteById(matchId);
    }

    /**
     * Return Match object, based on the given input
     * @param matchId, ID of the Match object to find
     * @return MatchOutputDto describing Match
     */
    public MatchOutputDto findMatchById(Integer matchId) {
        Match m = matchRepository.findById(matchId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format("match with id %d not found", matchId)
        ));
        return matchMapper.MatchToOutputRecord(m);
    }

    public List<MatchOutputDto> retrieveMatches() {
        List<Match> matchList = matchRepository.findAll();
        return matchList.stream().map(matchMapper::MatchToOutputRecord).toList();
    }

    /* MATCH ODD OPERATIONS */

    public String addOdd (@NotNull MatchOddInputDto oddDto) {
        MatchOdd beanBeforeSave = matchOddMapper.requestDtoToMatchOddBean(oddDto);
        return matchOddRepository.save(beanBeforeSave).getId().toString();
    }

    public MatchOddOutputDto updateOdd (
            Integer id, @NotNull MatchOddInputDto oddDto) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("PathVariable matchOddId not provided. Please check again");
        }
        if (!matchOddRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", id));
        }
        MatchOdd matchOddToUpdate = matchOddRepository.getReferenceById(id);
        matchOddToUpdate.setOdd(oddDto.odd());
        matchOddToUpdate.setSpecifier(stringToSpecifier(oddDto.specifier()));
        return matchOddMapper.matchOddBeanToResponseDto(matchOddRepository.save(matchOddToUpdate));
    }

    public void deleteOdd(@NotNull Integer oddId) {
        if (matchOddRepository.existsById(oddId)) {
            matchOddRepository.deleteById(oddId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", oddId));
        }
    }

    public void deleteAllOdds() {
        matchOddRepository.deleteAll();
    }

    public MatchOddOutputDto getOdd(@NotNull Integer oddId) {
        return matchOddMapper.matchOddBeanToResponseDto(
            matchOddRepository.findById(oddId)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("\"Match Odd\" with id %d not found", oddId)))
        );
    }

    public Collection<MatchOddOutputDto> retrieveOdds() {
        List<MatchOdd> allOdds = matchOddRepository.findAll();
        return allOdds.stream().map(matchOddMapper::matchOddBeanToResponseDto).toList();
    }
}
