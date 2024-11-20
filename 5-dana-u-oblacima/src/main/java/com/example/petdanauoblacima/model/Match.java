package com.example.petdanauoblacima.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_1_id", nullable = false)
    private Team team1;
    @ManyToOne
    @JoinColumn(name = "team_2_id", nullable = false)
    private Team team2;
    @ManyToOne
    @JoinColumn(name = "winning_team_id", nullable = true)
    private Team winningTeam;

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", winningTeam=" + winningTeam +
                '}';
    }
}
