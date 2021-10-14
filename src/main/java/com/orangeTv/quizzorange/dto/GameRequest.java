package com.orangeTv.quizzorange.dto;

import com.orangeTv.quizzorange.entities.Game;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GameRequest {
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }
}
