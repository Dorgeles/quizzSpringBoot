package com.orangeTv.quizzorange.controllers;

import java.util.*;

import com.orangeTv.quizzorange.dto.GameRequest;
import com.orangeTv.quizzorange.entities.Game;
import com.orangeTv.quizzorange.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/api/games")
    public Game createGame(@RequestBody GameRequest request) {
        return gameRepository.save(request.getGame());
    }

    @GetMapping("/api/games")
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    @DeleteMapping("/api/games/delete/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable("id") int id) throws Exception {
        Optional<Game> findGame = gameRepository.findById(id);
        Game _game = findGame.get();
        if (_game != null) {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new Exception("error");
        }
    }
}
