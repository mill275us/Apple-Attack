/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author CMMiller
 */
public class Game {
    
    private boolean gameOver;
    private int nextFreeLife;
    private int lives;
    private int level;
    private int score;
    private int highScore;
    private boolean newHighScore;
    private String statusMessage;
    private File scoreFile;


    public Game(int lives, int level, int score) {
        this.lives = lives;
        this.level = level;
        this.score = score;
        nextFreeLife = 10000;
        newHighScore = false;
        gameOver = false;
        scoreFile = new File("score.dat");
        
        // Load high score from file
        try {
            Scanner scanner = new Scanner(new FileReader(scoreFile));
            while(scanner.hasNext()) {
                String line = scanner.next();
                highScore = Integer.parseInt(line);
            }
        } catch (java.io.FileNotFoundException io) {
            highScore = 0;
            try {
                scoreFile.createNewFile();
            } 
            catch (Exception i) {
                highScore = 0;
            }
        }
    }
    
    public void increaseLevel() {
        this.level++;
    }
    
    public void loseLife() {
        this.lives--;
    }
    
    public void addPoints(int points) {
        score += points;
        if (score >= nextFreeLife) {
            lives++;
            nextFreeLife += 10000;
        }
        if (score > highScore) {
            newHighScore = true;
            highScore = score;
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

    public int getHighScore() {
        return highScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameIsOver() {
        gameOver = false;
        
        if (newHighScore) {
            
            // Save to file
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(scoreFile));
                System.out.println("Writing high score " + highScore);
                out.write("" + highScore);
                out.close();
                newHighScore = false;
            } catch (java.io.IOException e) {
                
            } 
        }
    }

 
    
    
}
