package com.endava.pocu.gamesApp.controllers;

import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // as a user I want to be able to get all games
    @GetMapping
    public List<Game> getGames(){return  gameService.findAllGames();}

    // as a user I want to be able to get a game by a specified id
    @GetMapping(value = "{id}")
    public Game getGameById(@PathVariable Long id){
        return gameService.findGameById(id);
    }

    // as a user I want to be able to select all games containing a specific tag
    @GetMapping(path = "/findByTag")
    public Optional<List<Game>> getGameByTagTitle(@RequestBody String tagTitle){
        return gameService.findByTagTitle(tagTitle);
    }

    // as a user I want to be able to add new games to my list
    @PostMapping
    public Game create(@RequestBody final Game game){
        return gameService.saveGame(game);
    }

    // as a user I want to be able to update information about a game specified by id
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Game update(@PathVariable Long id, @RequestBody Game game){
        return gameService.updateGame(id,game);
    }

    // as a user I want to be able to delete games from my list
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        gameService.deleteGame(id);
    }
}
