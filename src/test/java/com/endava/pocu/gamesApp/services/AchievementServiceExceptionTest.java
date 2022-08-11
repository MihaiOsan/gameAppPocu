package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.repositories.AchievementRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AchievementServiceExceptionTest {

    @Mock
    AchievementRepository achievementRepository;

    @InjectMocks
    AchievementService achievementService;

    private static final Long ACHIEVEMENT_ID = 1000l;
    private static final String ACHIEVEMENT_TITLE = "achievementTitle";
    private static final String ACHIEVEMENT_DESCRIPTION = "achievementDescription";
    private static final Game GAME = new Game();
    private static final String ACHIEVEMENT_DIFFICULTY = "achievementDifficulty";

    private static final Achievement ACHIEVEMENT = new Achievement();

    @BeforeAll
    static void setupAchievement(){
        ACHIEVEMENT.setAchievementDifficulty(ACHIEVEMENT_DIFFICULTY);
        ACHIEVEMENT.setAchievementId(ACHIEVEMENT_ID);
        ACHIEVEMENT.setAchievementDescription(ACHIEVEMENT_DESCRIPTION);
        ACHIEVEMENT.setAchievementTitle(ACHIEVEMENT_TITLE);
        ACHIEVEMENT.setGame(GAME);
    }

    @Test
    void saveAchievementShouldThrowInvalidRequestExceptionIfAchievementIsNull() {

        assertThatThrownBy(()-> achievementService.saveAchievement(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Achievement must not be null");
    }

    @Test
    void findAllAchievements() {

        List<Achievement> achievements = achievementService.findAllAchievements();
        assertThat(achievements.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findByGameTitleShouldThrowInvalidRequestExceptionIfGameTitleIsNull() {

        assertThatThrownBy(()-> achievementService.findByGameTitle(null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Game title must not be null");
    }

    @Test
    void updateAchievementShouldThrowRecordNotFoundWhenAchievementIsNotInDb() {
        when(achievementRepository.findById(ACHIEVEMENT_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> achievementService.updateAchievement(ACHIEVEMENT_ID,ACHIEVEMENT))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Achievement with id "+ACHIEVEMENT_ID+" does not exist");
    }

    @Test
    void updateAchievementShouldThrowInvalidRequestExceptionWhenAchievementOrIdIsNull() {

        assertThatThrownBy(()-> achievementService.updateAchievement(null ,null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Achievement and id must not be null");
    }

    @Test
    void deleteAchievementShouldThrowRecordNotFoundWhenAchievementIsNotInDb() {
        when(achievementRepository.findById(ACHIEVEMENT_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> achievementService.updateAchievement(ACHIEVEMENT_ID,ACHIEVEMENT))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Achievement with id "+ACHIEVEMENT_ID+" does not exist");
    }

    @Test
    void deleteAchievementShouldThrowInvalidRequestExceptionWhenAchievementOrIdIsNull() {

        assertThatThrownBy(()-> achievementService.updateAchievement(null ,null))
                .isInstanceOf(InvalidRequestException.class)
                .hasMessage("Achievement and id must not be null");
    }
}