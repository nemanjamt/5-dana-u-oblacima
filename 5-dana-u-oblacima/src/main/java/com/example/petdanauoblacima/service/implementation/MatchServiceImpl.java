package com.example.petdanauoblacima.service.implementation;

import com.example.petdanauoblacima.dto.match.MatchCreateDto;
import com.example.petdanauoblacima.model.Match;
import com.example.petdanauoblacima.model.Player;
import com.example.petdanauoblacima.model.Team;
import com.example.petdanauoblacima.repository.MatchRepository;
import com.example.petdanauoblacima.service.MatchService;
import com.example.petdanauoblacima.service.PlayerService;
import com.example.petdanauoblacima.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final TeamService teamService;

    public MatchServiceImpl(MatchRepository matchRepository, PlayerService playerService, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public void addMatch(MatchCreateDto matchCreateDto) {
        Match match = new Match();

        Team team1 = teamService.findTeamById(matchCreateDto.getTeam1Id());
        Team team2 = teamService.findTeamById(matchCreateDto.getTeam2Id());

        match.setTeam1(team1);
        match.setTeam2(team2);

        if(matchCreateDto.getWinningTeamId() != null) {
            Team winningTeam = teamService.findTeamById(matchCreateDto.getWinningTeamId());
            match.setWinningTeam(winningTeam);
        }

        boolean winnerTeam1 = matchCreateDto.getWinningTeamId().equals(team1.getId());
        boolean winnerTeam2 = matchCreateDto.getWinningTeamId().equals(team2.getId());
        double averageEloTeam1 = calculateAverageElo(team1.getPlayers());
        double averageEloTeam2 = calculateAverageElo(team2.getPlayers());

        updatePlayers(averageEloTeam1, matchCreateDto.getDuration(), team1, winnerTeam2, winnerTeam1);
        updatePlayers(averageEloTeam2, matchCreateDto.getDuration(), team2, winnerTeam1, winnerTeam2);

        matchRepository.save(match);
    }

    private void updatePlayers(double averageOpponentsElo, int matchDuration, Team team, boolean winnerTeam1, boolean winnerTeam2) {
        for(Player player : team.getPlayers()) {
            player.setHoursPlayed(player.getHoursPlayed() + matchDuration);

            player.setRatingAdjustment(calculateConstantK(player.getHoursPlayed()));

            if (winnerTeam2) {
                player.setWins(player.getWins() + 1);
                player.setElo(calculateNewElo(averageOpponentsElo, player.getElo(), player.getRatingAdjustment(), 1));
            } else if (winnerTeam1){
                player.setLosses(player.getLosses() + 1);
                player.setElo(calculateNewElo(averageOpponentsElo, player.getElo(), player.getRatingAdjustment(), 0));
            } else {
                player.setElo(calculateNewElo(averageOpponentsElo, player.getElo(), player.getRatingAdjustment(), 0.5));
            }

            playerService.save(player);
        }
    }

    public double calculateNewElo(double averageOpponentsElo, double playerCurrentElo, int k, double s){
        double expectedEla = 1/(1 + Math.pow(10, ((averageOpponentsElo - playerCurrentElo) /400)));
        return playerCurrentElo + k * (s - expectedEla);
    }

    public double calculateAverageElo(List<Player> players){
        return players.stream()
                .mapToDouble(Player::getElo)
                .average()
                .getAsDouble();
    }

    public int calculateConstantK(int hours){
        if(hours < 500){
            return 50;
        } else if(hours < 1000){
            return 40;
        } else if (hours < 3000){
            return 30;
        } else if (hours < 5000){
            return 20;
        } else {
            return 10;
        }
    }
}
