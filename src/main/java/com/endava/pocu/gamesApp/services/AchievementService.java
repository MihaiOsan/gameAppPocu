package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.repositories.AchievementRepository;
import org.springframework.beans.BeanUtils;
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
        if (achievement == null)
            throw new InvalidRequestException("Achievement must not be null");
        return achievementRepository.saveAndFlush(achievement);
    }

    public List<Achievement> findAllAchievements(){
        final List<Achievement> achievements = new ArrayList<>();
        achievementRepository.findAll().forEach(achievement -> achievements.add(achievement));
        return achievements;
    }

    public Optional<List<Achievement>> findByGameTitle(String gameTitle){
        if (gameTitle == null)
            throw new InvalidRequestException("Game title must not be null");
        return achievementRepository.findAchievementByGameTitle(gameTitle);
    }

    public Achievement updateAchievement(Long id, final Achievement achievement){
        if (id == null || achievement == null)
            throw new InvalidRequestException("Achievement and id must not be null");
        Optional<Achievement> optionalExistingAchievement = achievementRepository.findById(id);
        if (optionalExistingAchievement.isEmpty())
            throw new RecordNotFoundException("Achievement with id "+id+" does not exist");

        Achievement existingAchievement = optionalExistingAchievement.get();
        BeanUtils.copyProperties(achievement, existingAchievement, "achievementId");
        return achievementRepository.saveAndFlush(existingAchievement);
    }

    public void deleteAchievement(final Long id){
        if (id == null)
            throw new InvalidRequestException("Id must not be null");
        Optional<Achievement> optionalExistingAchievement = achievementRepository.findById(id);
        if (optionalExistingAchievement.isEmpty())
            throw new RecordNotFoundException("Achievement with id "+id+" does not exist");
        achievementRepository.deleteById(id);
    }
}
