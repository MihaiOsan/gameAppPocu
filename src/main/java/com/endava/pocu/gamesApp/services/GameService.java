package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.models.Game;
import com.endava.pocu.gamesApp.repositories.GameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game saveGame(final Game game){
        return gameRepository.saveAndFlush(game);
    }

    public List<Game> findAllGames(){
        final List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(game -> games.add(game));
        return games;
    }

    public Optional<List<Game>> findByTagTitle(String tagTitle){
        return gameRepository.findGamesByTagTitle(tagTitle);
    }

    public Game findGameById (final Long id){
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent())
            return game.get();
        else
            throw new RuntimeException("Could not find a game with id: " + id);
    }

    public Game updateGame(Long id, Game game){
            Game existingGame = gameRepository.getReferenceById(id);
            BeanUtils.copyProperties(game, existingGame, "gameId");
            return gameRepository.saveAndFlush(existingGame);
    }
    public void deleteGame(final Long id){
        gameRepository.deleteById(id);
    }
}
