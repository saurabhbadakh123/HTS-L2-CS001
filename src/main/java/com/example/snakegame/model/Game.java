package com.example.snakegame.model;

import java.util.Random;

public class Game {
    private Snake snake;
    private Point food;
    private int gridSize = 20;
    private int score;
    private boolean gameOver;
    private Random random;

    public Game() {
        snake = new Snake();
        random = new Random();
        spawnFood();
        score = 0;
        gameOver = false;
    }

    public void update() {
        if (gameOver) return;

        snake.move();

        // Check collision
        if (snake.checkCollision(gridSize)) {
            gameOver = true;
            return;
        }

        // Check food collision
        if (snake.getHead().equals(food)) {
            snake.grow();
            score++;
            spawnFood();
        }
    }

    private void spawnFood() {
        boolean onSnake;
        Point newFood;
        do {
            onSnake = false;
            newFood = new Point(random.nextInt(gridSize), random.nextInt(gridSize));
            
            for (Point segment : snake.getBody()) {
                if (segment.equals(newFood)) {
                    onSnake = true;
                    break;
                }
            }
        } while (onSnake);
        
        food = newFood;
    }

    public void changeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public GameState getGameState() {
        GameState state = new GameState();
        state.setSnake(snake.getBody());
        state.setFood(food);
        state.setScore(score);
        state.setGameOver(gameOver);
        state.setDirection(snake.getDirection());
        return state;
    }

    public void reset() {
        snake = new Snake();
        spawnFood();
        score = 0;
        gameOver = false;
    }

    // Getters
    public boolean isGameOver() { return gameOver; }
}