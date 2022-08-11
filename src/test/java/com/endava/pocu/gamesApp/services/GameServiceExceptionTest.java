package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.models.Tag;
import com.endava.pocu.gamesApp.repositories.GameRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceExceptionTest {

    @Mock
    GameRepository gameRepository;

    @InjectMocks
    GameService gameService;

    private static final Long GAME_ID = 1000l;
    private static final String GAME_TITLE = "gmaeTitle";
    private static final String GAME_DESCRIPION = "gameDescription";
    private static final List<Tag> TAGS = new ArrayList<>();
    private static final String GAME_DEVELOPER = "gameDeveloper";
    private static final String GAME_AGE_RATING = "gameAgeRating";
    private static final float GAME_RATING = 10.0f;
    private static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    private static final Game GAME = new Game();

    @BeforeAll
    static void setupGAME(){
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
    void saveGame() {

        assertThatThrownBy(()-> gameService.saveGame(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Game must not be null");
    }

    @Test
    void findAllGames() {

        List<Game> games = gameService.findAllGames();
        assertThat(games.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findByTagTitleShouldThrowInvalidRequestExceptionIfTagTitleIsNull() {

        assertThatThrownBy(()-> gameService.findByTagTitle(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Tag title must not be null");
    }

    @Test
    void findGameByIdShouldThrowRecordNotFoundWhenGameIsNotInDb(){
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> gameService.findGameById(GAME_ID))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Game with id "+GAME_ID+" does not exist");
    }

    @Test
    void updateGameShouldThrowRecordNotFoundWhenGameIsNotInDb() {
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> gameService.updateGame(GAME_ID,GAME))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Game with id "+GAME_ID+" does not exist");
    }

    @Test
    void updateGameShouldThrowRecordNotFoundWhenAchievementIsNotInDb() {
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> gameService.updateGame(GAME_ID,GAME))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Game with id "+GAME_ID+" does not exist");
    }

    @Test
    void updateGameShouldThrowInvalidRequestExceptionWhenGameOrIdIsNull() {

        assertThatThrownBy(()->gameService.updateGame(null ,null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Game and id must not be null");
    }

    @Test
    void deleteGameShouldThrowRecordNotFoundWhenAchievementIsNotInDb() {
        when(gameRepository.findById(GAME_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> gameService.updateGame(GAME_ID,GAME))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Game with id "+GAME_ID+" does not exist");
    }

    @Test
    void deleteGameShouldThrowInvalidRequestExceptionWhenAchievementOrIdIsNull() {

        assertThatThrownBy(()-> gameService.updateGame(null ,null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Game and id must not be null");
    }
}