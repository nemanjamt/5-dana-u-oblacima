package com.example.petdanauoblacima.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class PlayerRepositoryUnitTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testExistsByNickname() {
        assertThat(playerRepository.existsByNickname("Player1")).isTrue();
        assertThat(playerRepository.existsByNickname("NonExistentPlayer")).isFalse();
    }

    @Test
    public void testExistsByNicknameAndTeamIsNull() {
        assertThat(playerRepository.existsByNicknameAndTeamIsNull("Player11")).isTrue();
        assertThat(playerRepository.existsByNicknameAndTeamIsNull("Player1")).isFalse();
    }

}
