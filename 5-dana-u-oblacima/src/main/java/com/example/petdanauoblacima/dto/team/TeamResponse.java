package com.example.petdanauoblacima.dto.team;

import com.example.petdanauoblacima.dto.player.PlayerResponse;
import lombok.Data;

import java.util.List;

@Data
public class TeamResponse {
    private Long id;
    private String teamName;
    private List<PlayerResponse> players;
}
