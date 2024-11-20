package com.example.petdanauoblacima.service.implementation;

import com.example.petdanauoblacima.dto.player.PlayerResponse;
import com.example.petdanauoblacima.dto.team.TeamCreateDto;
import com.example.petdanauoblacima.dto.team.TeamResponse;
import com.example.petdanauoblacima.exception.TeamAlreadyAssigned;
import com.example.petdanauoblacima.model.Player;
import com.example.petdanauoblacima.model.Team;
import com.example.petdanauoblacima.repository.PlayerRepository;
import com.example.petdanauoblacima.repository.TeamRepository;
import com.example.petdanauoblacima.service.PlayerService;
import com.example.petdanauoblacima.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerService playerService, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    public TeamResponse getTeamById(Long teamId) {
        Team team = findTeamById(teamId);
        return teamToResponse(team);
    }

    public Team findTeamById(Long teamId) {
        if(!teamRepository.existsById(teamId)){
            throw new EntityNotFoundException("Team with id " + teamId + " not found");
        }
        return teamRepository.findById(teamId).get();
    }

    public TeamResponse createTeam(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        if(teamRepository.existsByTeamName(teamCreateDto.getTeamName())) {
            throw new TeamAlreadyAssigned("Team with name " + teamCreateDto.getTeamName() + " already exists");
        }
        team.setTeamName(teamCreateDto.getTeamName());
        Team savedTeam = teamRepository.save(team);
        List<Player> players = new ArrayList<>();
        for(Long playerId : teamCreateDto.getPlayers()) {
            Player player = playerService.findPlayerById(playerId);
            if(!playerRepository.existsByNicknameAndTeamIsNull(player.getNickname())){
                throw new TeamAlreadyAssigned("Player with id " + playerId + " already has assigned team");
            }
            player.setTeam(savedTeam);
            playerService.save(player);
            players.add(player);
        }
        savedTeam.setPlayers(players);

        return teamToResponse(savedTeam);
    }

    public TeamResponse teamToResponse(Team team) {
        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setTeamName(team.getTeamName());
        teamResponse.setId(team.getId());

        List<PlayerResponse> playerResponses = new ArrayList<>();

        for(Player player : team.getPlayers()) {
            PlayerResponse playerResponse = playerService.playerToResponse(player);
            playerResponses.add(playerResponse);
        }

        teamResponse.setPlayers(playerResponses);
        return teamResponse;
    }

}
