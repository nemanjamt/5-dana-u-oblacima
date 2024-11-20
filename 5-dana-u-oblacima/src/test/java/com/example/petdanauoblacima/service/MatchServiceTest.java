package com.example.petdanauoblacima.service;

import com.example.petdanauoblacima.model.Player;
import com.example.petdanauoblacima.service.implementation.MatchServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchServiceTest {
    private final MatchServiceImpl matchService = new MatchServiceImpl(null, null, null);

    @Test
    void calculateNewElo_shouldReturnCorrectElo() {
        double averageOpponentsElo = 1500.0;
        double playerCurrentElo = 1400.0;
        int k = 30;
        double s = 1;

        double newElo = matchService.calculateNewElo(averageOpponentsElo, playerCurrentElo, k, s);

        assertEquals(1419.20, newElo, 0.01);
    }

    @Test
    void calculateNewElo_whenDraw_shouldReturnCorrectElo() {
        double averageOpponentsElo = 1500.0;
        double playerCurrentElo = 1400.0;
        int k = 30;
        double s = 0.5; // Draw

        double newElo = matchService.calculateNewElo(averageOpponentsElo, playerCurrentElo, k, s);

        assertEquals(1404.20, newElo, 0.01); // Delta is 0.01 for floating-point precision
    }

    @Test
    void calculateAverageElo_shouldReturnCorrectAverage() {
        Player player1 = new Player();
        player1.setElo(1200.0);
        Player player2 = new Player();
        player2.setElo(1400.0);
        Player player3 = new Player();
        player3.setElo(1600.0);
        List<Player> players = List.of(player1, player2, player3);

        double averageElo = matchService.calculateAverageElo(players);

        assertEquals(1400.0, averageElo);
    }

    @Test
    void calculateConstantK_shouldReturnCorrectK() {
        assertEquals(50, matchService.calculateConstantK(100));  // Less than 500 hours
        assertEquals(40, matchService.calculateConstantK(700));  // Between 500 and 999 hours
        assertEquals(30, matchService.calculateConstantK(1500)); // Between 1000 and 2999 hours
        assertEquals(20, matchService.calculateConstantK(3500)); // Between 3000 and 4999 hours
        assertEquals(10, matchService.calculateConstantK(5000)); // 5000 or more hours
    }
}
