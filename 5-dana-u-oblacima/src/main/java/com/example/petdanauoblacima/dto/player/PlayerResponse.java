package com.example.petdanauoblacima.dto.player;

import lombok.Data;

@Data
public class PlayerResponse {
    private Long id;
    private String nickname;
    private int wins;
    private int losses;
    private double elo;
    private int hoursPlayed;
    private Long teamId;
    private Integer ratingAdjustment;

}
