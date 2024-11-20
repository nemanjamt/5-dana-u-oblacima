package com.example.petdanauoblacima.dto.player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayerCreateDto {
    @NotNull(message = "nickname cannot be null")
    @NotBlank(message = "nickname cannot be blank")
    private String nickname;
}
