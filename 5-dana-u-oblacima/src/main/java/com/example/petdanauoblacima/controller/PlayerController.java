package com.example.petdanauoblacima.controller;

import com.example.petdanauoblacima.dto.player.PlayerCreateDto;
import com.example.petdanauoblacima.dto.player.PlayerResponse;
import com.example.petdanauoblacima.model.Player;
import com.example.petdanauoblacima.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody PlayerCreateDto playerCreateDto) {
        return new ResponseEntity<>(playerService.createPlayer(playerCreateDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable Long id) {
        return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }
}
