package com.example.petdanauoblacima.service.implementation;

import com.example.petdanauoblacima.dto.player.PlayerCreateDto;
import com.example.petdanauoblacima.dto.player.PlayerResponse;
import com.example.petdanauoblacima.exception.NicknameAlreadyExists;
import com.example.petdanauoblacima.model.Player;
import com.example.petdanauoblacima.repository.PlayerRepository;
import com.example.petdanauoblacima.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerResponse createPlayer(PlayerCreateDto playerCreateDto) {
        Player player = new Player();
        if(playerRepository.existsByNickname(playerCreateDto.getNickname())) {
            throw new NicknameAlreadyExists("Player with nickname " + playerCreateDto.getNickname() + " already exists");
        }
        player.setNickname(playerCreateDto.getNickname());
        return playerToResponse(playerRepository.save(player));
    }

    public PlayerResponse getPlayer(Long id) {
        Player player = findPlayerById(id);
        return playerToResponse(player);
    }

    public Player findPlayerById(Long id) {
        if(!playerRepository.existsById(id)){
            throw new EntityNotFoundException("Player with id " + id + " not found");
        }
        return playerRepository.findById(id).get();
    }

    public PlayerResponse playerToResponse(Player player) {
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setNickname(player.getNickname());
        playerResponse.setId(player.getId());
        playerResponse.setElo(player.getElo());
        playerResponse.setLosses(player.getLosses());
        playerResponse.setHoursPlayed(player.getHoursPlayed());
        playerResponse.setWins(player.getWins());
        playerResponse.setRatingAdjustment(player.getRatingAdjustment());
        if(player.getTeam() != null) {
            playerResponse.setTeamId(player.getTeam().getId());
        }
        return playerResponse;
    }

    public boolean existByNicknameAndTeamNotAssigned(String nickname) {
        return playerRepository.existsByNicknameAndTeamIsNull(nickname);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<PlayerResponse> findAll() {
        return playerRepository.findAll().stream()
                .map(this::playerToResponse)
                .collect(Collectors.toList());
    }
}
