package com.example.petdanauoblacima.dto.match;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchCreateDto {
    @NotNull
    private Long team1Id;
    @NotNull
    private Long team2Id;
    private Long winningTeamId;
    @Min(value = 1, message = "Duration must be greater than 0")
    private Integer duration;
}
