package com.example.snakegame.service;

import com.example.snakegame.model.Direction;
import com.example.snakegame.model.Game;
import com.example.snakegame.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;

    public GameService() {
        resetGame();
    }

    public GameState updateGame() {
        game.update();
        return game.getGameState();
    }

    public void changeDirection(Direction direction) {
        game.changeDirection(direction);
    }

    public GameState getGameState() {
        return game.getGameState();
    }

    public void resetGame() {
        game = new Game();
    }
}