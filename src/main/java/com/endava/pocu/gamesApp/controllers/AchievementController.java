package com.endava.pocu.gamesApp.controllers;

import com.endava.pocu.gamesApp.models.Achievement;
import com.endava.pocu.gamesApp.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/achievements")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    // as a user I want to be able to see all achievements
    @GetMapping
    public List<Achievement> getAchievements(){return  achievementService.findAllAchievements();}

    // as a user I want to be able to see all achievements for a specific game
    @GetMapping(path = "/findByGameTitle")
    public Optional<List<Achievement>> getAchievementByGameTitle(@RequestBody String gameTitle){
        return achievementService.findByGameTitel(gameTitle);
    }

    // as a user I want to be able to add new achievements
    @PostMapping
    public void create(@RequestBody final Achievement achievement){
        achievementService.saveAchievement(achievement);
    }

    // as a user I want to be able to update achievements with a specified id
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody Achievement achievement){
        achievementService.updateAchievement(id,achievement);
    }

    //as a user I want to be able to delete achievements by their id
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        achievementService.deleteAchievement(id);
    }
}
