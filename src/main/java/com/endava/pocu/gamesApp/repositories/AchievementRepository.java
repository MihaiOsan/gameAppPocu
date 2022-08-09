package com.endava.pocu.gamesApp.repositories;

import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    @Query("select a from achievements a join a.game g  where g.gameTitle = :gameTitle")
    Optional<List<Achievement>> findAchievementByGameTitle(String gameTitle);
}
