package com.lygizos.sports_monitor.model.service;

import com.lygizos.sports_monitor.model.match.*;
import com.lygizos.sports_monitor.model.matchodds.MatchOddMapper;
import com.lygizos.sports_monitor.model.matchodds.MatchOddRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static com.lygizos.sports_monitor.Common.integerToSport;

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
        Match storedMatch = matchRepository.save(rawMatch);
        return storedMatch.getId().toString();
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
        Match updatedRecord = matchRepository.save(matchToUpdate);

        return matchMapper.MatchToOutputRecord(updatedRecord);
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
    public void deleteMatch(Integer matchId) {
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
}
