package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.GameRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameServiceTest {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    private static final Long GAME_ID = 1000l;
    private static final String GAME_TITLE = "gmaeTitle";
    private static final String GAME_DESCRIPION = "gameDescription";
    private static final List<Tag> TAGS = new ArrayList<Tag>();
    private static final String GAME_DEVELOPER = "gameDeveloper";
    private static final String GAME_AGE_RATING = "gameAgeRating";
    private static final float GAME_RATING = 10.0f;
    private static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    private static final Game GAME = new Game();

    @BeforeAll
    static void setupAchievement(){
        GAME.setGameId(GAME_ID);
        GAME.setGameTitle(GAME_TITLE);
        GAME.setGameDescription(GAME_DESCRIPION);
        GAME.setGameDeveloper(GAME_DEVELOPER);
        GAME.setGameAgeRating(GAME_AGE_RATING);
        GAME.setGameRating(GAME_RATING);
        GAME.setTags(TAGS);
        GAME.setGameAchievements(ACHIEVEMENTS);
    }

    @Test
    @Order(1)
    void saveGameShouldIncreaseNumberOfElementsInDB() {
        int beforeSize = gameRepository.findAll().size();

        gameService.saveGame(GAME);

        int afterSize = gameRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize-1);
    }

    @Test
    @Order(2)
    void saveGameSuccess() {

        gameService.saveGame(GAME);

        Game game = gameRepository.findById(1l).get();
        assertThat(game.getGameTitle()).isEqualTo(GAME.getGameTitle());
        assertThat(game.getGameDescription()).isEqualTo(GAME.getGameDescription());
    }

    @Test
    @Order(3)
    void updateGameShouldNotIncreaseNumberOfElementsInDB() {
        gameService.saveGame(GAME);
        int beforeSize = gameRepository.findAll().size();
        Game newGame = new Game();
        BeanUtils.copyProperties(newGame, GAME, "gameId");

        gameService.updateGame(1l, newGame);

        int afterSize = gameRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize);
    }

    @Test
    @Order(4)
    void updateGameSuccess() {
        gameService.saveGame(GAME);
        Game newGame = new Game();
        BeanUtils.copyProperties( GAME, newGame,"gameId");
        newGame.setGameDescription("CHANGED_DESCRIPTION");

        gameService.updateGame(1l, newGame);

        Game updatedGame = gameRepository.findById(1l).get();
        assertThat(updatedGame.getGameDescription()).isEqualTo("CHANGED_DESCRIPTION");

    }

    @Test
    @Order(5)
    void deleteGameShouldDecreaseNumberOfElementsInDB() {
        gameService.saveGame(GAME);
        int beforeSize = gameRepository.findAll().size();

        gameService.deleteGame(1l);

        int afterSize = gameRepository.findAll().size();
        assertThat(beforeSize-1).isEqualTo(afterSize);
    }

    @Test
    @Order(6)
    void deleteGameSuccess() {
        gameService.saveGame(GAME);

        gameService.deleteGame(2l);

        assertThatThrownBy(()-> gameService.findGameById(1l))
                .isInstanceOf(RecordNotFoundException.class);
    }
}
