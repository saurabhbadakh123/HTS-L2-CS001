package com.example.snakegame.model;

import java.util.List;

public class GameState {
    private List<Point> snake;
    private Point food;
    private int score;
    private boolean gameOver;
    private Direction direction;

    // Getters and setters
    public List<Point> getSnake() { return snake; }
    public void setSnake(List<Point> snake) { this.snake = snake; }
    public Point getFood() { return food; }
    public void setFood(Point food) { this.food = food; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }
}