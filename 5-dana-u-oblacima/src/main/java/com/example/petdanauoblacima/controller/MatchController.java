package com.example.petdanauoblacima.controller;

import com.example.petdanauoblacima.dto.match.MatchCreateDto;
import com.example.petdanauoblacima.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity createMatch(@Valid @RequestBody MatchCreateDto matchCreateDto) {
        matchService.addMatch(matchCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
