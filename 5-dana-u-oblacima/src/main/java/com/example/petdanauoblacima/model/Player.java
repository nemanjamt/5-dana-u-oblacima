package com.example.petdanauoblacima.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private int wins;
    private int losses;
    private double elo;
    private int hoursPlayed;
    private Integer ratingAdjustment;
    @ManyToOne
    private Team team;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", elo=" + elo +
                ", hoursPlayed=" + hoursPlayed +
                ", ratingAdjustment=" + ratingAdjustment +
                '}';
    }
}
