package com.endava.pocu.gamesApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "game_title")
    private String gameTitle;

    @Column(name = "game_description")
    private String gameDescription;

    @Column(name = "game_developer")
    private String gameDeveloper;

    @Column(name = "game_age_rating")
    private String gameAgeRating;

    @Column(name = "game_rating")
    private Float gameRating;

    @OneToMany(mappedBy = "game")
    private List<Achievement> gameAchievements;

    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public Game() {
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<Achievement> getGameAchievements() {
        return gameAchievements;
    }

    public void setGameAchievements(List<Achievement> gameAchievements) {
        this.gameAchievements = gameAchievements;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public String getGameAgeRating() {
        return gameAgeRating;
    }

    public void setGameAgeRating(String gameAgeRating) {
        this.gameAgeRating = gameAgeRating;
    }

    public Float getGameRating() {
        return gameRating;
    }

    public void setGameRating(Float gameRating) {
        this.gameRating = gameRating;
    }
}
