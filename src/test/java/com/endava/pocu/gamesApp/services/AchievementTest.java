package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.repositories.AchievementRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AchievementTest {

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    AchievementService achievementService;

    private static final Long ACHIEVEMENT_ID = 1000l;
    private static final String ACHIEVEMENT_TITLE = "achievementTitle";
    private static final String ACHIEVEMENT_DESCRIPTION = "achievementDescription";
    private static final String ACHIEVEMENT_DIFFICULTY = "achievementDifficulty";

    private static final Achievement ACHIEVEMENT = new Achievement();

    @BeforeAll
    static void setupAchievement(){

        ACHIEVEMENT.setAchievementDifficulty(ACHIEVEMENT_DIFFICULTY);
        ACHIEVEMENT.setAchievementId(ACHIEVEMENT_ID);
        ACHIEVEMENT.setAchievementDescription(ACHIEVEMENT_DESCRIPTION);
        ACHIEVEMENT.setAchievementTitle(ACHIEVEMENT_TITLE);
    }

    @Test
    @Order(1)
    void saveAchievementShouldIncreaseNumberOfElementsInDB() {
        int beforeSize = achievementRepository.findAll().size();

        achievementService.saveAchievement(ACHIEVEMENT);

        int afterSize = achievementRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize-1);
    }

    @Test
    @Order(2)
    void saveAchievementSuccess() {

        achievementService.saveAchievement(ACHIEVEMENT);

        Achievement achievement = achievementRepository.findById(1l).get();
        assertThat(achievement.getAchievementTitle()).isEqualTo(ACHIEVEMENT.getAchievementTitle());
        assertThat(achievement.getAchievementDescription()).isEqualTo(ACHIEVEMENT.getAchievementDescription());
    }

    @Test
    @Order(3)
    void updateAchievementShouldNotIncreaseNumberOfElementsInDB() {
        achievementService.saveAchievement(ACHIEVEMENT);
        int beforeSize = achievementRepository.findAll().size();
        Achievement newAchievement = new Achievement();
        BeanUtils.copyProperties(newAchievement, ACHIEVEMENT, "achievementId");

        achievementService.updateAchievement(1l, newAchievement);

        int afterSize = achievementRepository.findAll().size();
        assertThat(beforeSize).isEqualTo(afterSize);
    }

    @Test
    @Order(4)
    void updateAchievementSuccess() {
        achievementService.saveAchievement(ACHIEVEMENT);
        Achievement newAchievement = new Achievement();
        BeanUtils.copyProperties( ACHIEVEMENT, newAchievement,"achievementId");
        newAchievement.setAchievementDescription("CHANGED_DESCRIPTION");

        achievementService.updateAchievement(1l, newAchievement);

        Achievement updatedAchievement = achievementRepository.findById(1l).get();
        assertThat(updatedAchievement.getAchievementDescription()).isEqualTo("CHANGED_DESCRIPTION");

    }

    @Test
    @Order(5)
    void deleteAchievementShouldDecreaseNumberOfElementsInDB() {
        achievementService.saveAchievement(ACHIEVEMENT);
        int beforeSize = achievementRepository.findAll().size();

        achievementService.deleteAchievement(1l);

        int afterSize = achievementRepository.findAll().size();
        assertThat(beforeSize-1).isEqualTo(afterSize);
    }

    @Test
    @Order(6)
    void deleteAchievementSuccess() {
        achievementService.saveAchievement(ACHIEVEMENT);

        achievementService.deleteAchievement(2l);

        assertThat(achievementRepository.findById(2l))
                .isEqualTo(Optional.empty());
    }
}
