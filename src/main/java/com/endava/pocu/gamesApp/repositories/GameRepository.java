package com.endava.pocu.gamesApp.repositories;

import com.endava.pocu.gamesApp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from games g join g.tags t  where t.tagTitle = :tagTitle")
    Optional<List<Game>> findGamesByTagTitle(String tagTitle);
}
