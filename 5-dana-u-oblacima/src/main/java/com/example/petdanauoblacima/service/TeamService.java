package com.example.petdanauoblacima.service;

import com.example.petdanauoblacima.dto.team.TeamCreateDto;
import com.example.petdanauoblacima.dto.team.TeamResponse;
import com.example.petdanauoblacima.model.Team;

public interface TeamService {
    TeamResponse createTeam(TeamCreateDto teamCreateDto);
    TeamResponse getTeamById(Long teamId);
    Team findTeamById(Long teamId);
}
