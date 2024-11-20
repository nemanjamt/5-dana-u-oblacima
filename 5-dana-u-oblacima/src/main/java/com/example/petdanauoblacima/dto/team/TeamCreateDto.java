package com.example.petdanauoblacima.dto.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.List;

@Data
public class TeamCreateDto {
    @NotNull(message = "Team name cannot be null")
    @NotBlank(message = "Team name cannot be blank")
    private String teamName;

    @NotNull(message = "Players list cannot be null")
    @Size(min = 5, max = 5, message = "Players list must contain exactly 5 players")
    private List<Long> players;
}
