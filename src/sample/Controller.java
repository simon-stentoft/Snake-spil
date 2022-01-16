package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    //Variables
    private int width = 20;
    private int height = 20;
    private int appleX = 0;
    private int appleY = 0;
    private int speed = 3;
    private int cornerSize = 25;
    private List<Corner> snake = new ArrayList<>();
    private Dir direction = Dir.left;
    private boolean gameOver = false;
    private Random rand = new Random();

    public enum Dir {
        left, right, up, down
    }







    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public int getCornerSize() {
        return cornerSize;
    }

    public void setCornerSize(int cornerSize) {
        this.cornerSize = cornerSize;
    }

    public List<Corner> getSnake() {
        return snake;
    }

    public void setSnake(List<Corner> snake) {
        this.snake = snake;
    }

    public Dir getDirection() {
        return direction;
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
