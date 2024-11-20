package com.example.petdanauoblacima.service;

import com.example.petdanauoblacima.dto.player.PlayerCreateDto;
import com.example.petdanauoblacima.dto.player.PlayerResponse;
import com.example.petdanauoblacima.model.Player;

import java.util.List;

public interface PlayerService {
    PlayerResponse createPlayer(PlayerCreateDto playerCreateDto);
    PlayerResponse getPlayer(Long id);
    Player findPlayerById(Long id);
    PlayerResponse playerToResponse(Player player);
    boolean existByNicknameAndTeamNotAssigned(String nickname);
    Player save(Player player);
    List<PlayerResponse> findAll();
}
