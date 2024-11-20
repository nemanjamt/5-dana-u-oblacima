package com.example.petdanauoblacima.controller;

import com.example.petdanauoblacima.dto.team.TeamCreateDto;
import com.example.petdanauoblacima.dto.team.TeamResponse;
import com.example.petdanauoblacima.model.Team;
import com.example.petdanauoblacima.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> findTeamById(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamResponse> addTeam(@Valid @RequestBody TeamCreateDto teamCreateDto) {
        return new ResponseEntity<>(teamService.createTeam(teamCreateDto), HttpStatus.OK);
    }
}
