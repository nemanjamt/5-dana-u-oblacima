package com.example.petdanauoblacima.repository;

import com.example.petdanauoblacima.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Boolean existsByTeamName(String teamName);
}
