package com.orangeTv.quizzorange.controllers;

import java.util.List;

import com.orangeTv.quizzorange.dto.GameRequest;
import com.orangeTv.quizzorange.entities.Game;
import com.orangeTv.quizzorange.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
