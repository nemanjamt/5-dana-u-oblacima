package com.example.petdanauoblacima.repository;

import com.example.petdanauoblacima.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Boolean existsByNickname(String nickname);
    boolean existsByNicknameAndTeamIsNull(String nickname);
}
