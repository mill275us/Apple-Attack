/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author CMMiller
 */
public class Game {
    
    private int nextFreeLife;
    private int lives;
    private int level;
    private int score;
    private String statusMessage;

    public Game(int lives, int level, int score) {
        this.lives = lives;
        this.level = level;
        this.score = score;
        nextFreeLife = 10000;
    }
    
    public void increaseLevel() {
        this.level++;
    }
    
    public void loseLife() {
        this.lives--;
    }
    
    public void addPoints(int points) {
        score += points;
        if (score > nextFreeLife) {
            lives++;
            nextFreeLife += 10000;
        }
    }
    
    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
}
