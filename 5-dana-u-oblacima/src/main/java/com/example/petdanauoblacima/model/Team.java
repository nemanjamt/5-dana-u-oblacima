package com.example.petdanauoblacima.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    @OneToMany(mappedBy = "team")
    List<Player> players;

    @OneToMany(mappedBy = "team1")
    private List<Match> matchesAsTeam1;


    @OneToMany(mappedBy = "team2")
    private List<Match> matchesAsTeam2;


    @OneToMany(mappedBy = "winningTeam")
    private List<Match> matchesAsWinner;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", players=" + players +
                '}';
    }
}
