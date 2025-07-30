package com.example.snakegame.model;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> body;
    private Direction direction;
    private boolean growing;

    public Snake() {
        body = new ArrayList<>();
        // Initialize snake with 3 segments
        body.add(new Point(10, 10));
        body.add(new Point(10, 11));
        body.add(new Point(10, 12));
        direction = Direction.UP;
        growing = false;
    }

    public List<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        // Prevent 180-degree turns
        if ((this.direction == Direction.UP && direction != Direction.DOWN) ||
            (this.direction == Direction.DOWN && direction != Direction.UP) ||
            (this.direction == Direction.LEFT && direction != Direction.RIGHT) ||
            (this.direction == Direction.RIGHT && direction != Direction.LEFT)) {
            this.direction = direction;
        }
    }

    public void move() {
        Point head = getHead();
        Point newHead = new Point(head.getX(), head.getY());

        switch (direction) {
            case UP:
                newHead.setY(newHead.getY() - 1);
                break;
            case DOWN:
                newHead.setY(newHead.getY() + 1);
                break;
            case LEFT:
                newHead.setX(newHead.getX() - 1);
                break;
            case RIGHT:
                newHead.setX(newHead.getX() + 1);
                break;
        }

        body.add(0, newHead);
        if (!growing) {
            body.remove(body.size() - 1);
        } else {
            growing = false;
        }
    }

    public void grow() {
        growing = true;
    }

    public Point getHead() {
        return body.get(0);
    }

    public boolean checkCollision(int gridSize) {
        Point head = getHead();
        
        // Check wall collision
        if (head.getX() < 0 || head.getX() >= gridSize || 
            head.getY() < 0 || head.getY() >= gridSize) {
            return true;
        }
        
        // Check self collision (skip head)
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        
        return false;
    }
}