package com.endava.pocu.gamesApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Game game;

    @Column(name = "achievement_title")
    private String achievementTitle;

    @Column(name = "achievement_description")
    private String achievementDescription;

    @Column(name = "achievement_dificulty")
    private String achievementDifficulty;

    public Achievement() {
    }

    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getAchievementTitle() {
        return achievementTitle;
    }

    public void setAchievementTitle(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }

    public String getAchievementDifficulty() {
        return achievementDifficulty;
    }

    public void setAchievementDifficulty(String achievementDificulty) {
        this.achievementDifficulty = achievementDificulty;
    }
}
