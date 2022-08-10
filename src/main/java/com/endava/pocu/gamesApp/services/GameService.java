package com.endava.pocu.gamesApp.services;

import com.endava.pocu.gamesApp.exceptions.InvalidRequestException;
import com.endava.pocu.gamesApp.exceptions.RecordNotFoundException;
import com.endava.pocu.gamesApp.models.Achievement;
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
        if (game == null)
            throw new InvalidRequestException("Game must not be null");
        return gameRepository.saveAndFlush(game);
    }

    public List<Game> findAllGames(){
        final List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(game -> games.add(game));
        return games;
    }

    public Optional<List<Game>> findByTagTitle(String tagTitle){
        if (tagTitle == null)
            throw new InvalidRequestException("Tag title must not be null");
        return gameRepository.findGamesByTagTitle(tagTitle);
    }

    public Game findGameById (final Long id){
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent())
            return game.get();
        else
            throw new RecordNotFoundException("Game with id "+id+" does not exist");
    }

    public Game updateGame(Long id, Game game){

        if (id == null || game == null)
            throw new InvalidRequestException("Game and id must not be null");
        Optional<Game> optionalExistingGame = gameRepository.findById(id);
        if (optionalExistingGame.isEmpty())
            throw new RecordNotFoundException("Game with id "+id+" does not exist");

        Game existingGame = optionalExistingGame.get();;
        BeanUtils.copyProperties(game, existingGame, "gameId");
        return gameRepository.saveAndFlush(existingGame);
    }
    public void deleteGame(final Long id){
        if (id == null)
            throw new InvalidRequestException("Id must not be null");
        Optional<Game> optionalExistingGame = gameRepository.findById(id);
        if (optionalExistingGame.isEmpty())
            throw new RecordNotFoundException("Game with id "+id+" does not exist");
        gameRepository.deleteById(id);
    }
}
