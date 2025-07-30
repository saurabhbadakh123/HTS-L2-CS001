package com.example.snakegame.controller;

import com.example.snakegame.model.Direction;
import com.example.snakegame.model.GameState;
import com.example.snakegame.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private final GameService gameService;

    // Constructor injection without @Autowired (recommended for Spring 4.3+)
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String game(Model model) {
        return "game";
    }

    @MessageMapping("/update")
    @SendTo("/topic/gamestate")
    public GameState updateGame() {
        return gameService.updateGame();
    }

    @MessageMapping("/direction")
    public void changeDirection(Direction direction) {
        gameService.changeDirection(direction);
    }

    @MessageMapping("/reset")
    @SendTo("/topic/gamestate")
    public GameState resetGame() {
        gameService.resetGame();
        return gameService.getGameState();
    }
}