package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.repositories.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement saveAchievement(final Achievement achievement){
        return achievementRepository.saveAndFlush(achievement);
    }

    public List<Achievement> findAllAchievements(){
        final List<Achievement> achievements = new ArrayList<>();
        achievementRepository.findAll().forEach(achievement -> achievements.add(achievement));
        return achievements;
    }

    public Optional<List<Achievement>> findByGameTitel(String gameTitle){
        return achievementRepository.findAchievementByGameTitle(gameTitle);
    }

    public Achievement updateAchievement(Long id, final Achievement achievement){
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(final Long id){
        achievementRepository.deleteById(id);
    }
}
