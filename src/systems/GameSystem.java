/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.EntityCreator;
import entitysystem.EntityRemover;
import entitysystem.Engine;
import java.util.ArrayList;
import systems.ISystem;
import nodes.GameNode;
import components.Game;
import components.User;
import entitysystem.Entity;
import javax.swing.JPanel;
import nodes.*;

/**
 *
 * @author CMMiller
 */
public class GameSystem implements ISystem {
    
    private EntityCreator creator;
    private EntityRemover remover;
    private Engine engine;
    private ArrayList<GameNode> gameNodeList;
    private ArrayList<UserCollisionNode> userCollisionNodeList;
    private ArrayList<BulletCollisionNode> bulletCollisionNodeList;
    private ArrayList<AsteroidCollisionNode> asteroidCollisionNodeList;
    private JPanel jpanel;

    public GameSystem(EntityCreator creator, EntityRemover remover, JPanel jpanel) {
        this.creator = creator;
        this.remover = remover;
        this.jpanel = jpanel;
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        gameNodeList = this.engine.getGameNodeList();
        asteroidCollisionNodeList = this.engine.getAsteroidCollisionNodeList(); 
        bulletCollisionNodeList = this.engine.getBulletCollisionNodeList();
        userCollisionNodeList = this.engine.getUserCollisionNodeList();
    }

    @Override
    public void update(int time) {
        
        if (gameNodeList != null && !gameNodeList.isEmpty()) {
            
            Game game = (Game)gameNodeList.get(0).entity.get("components.Game");
            
            if (userCollisionNodeList != null && userCollisionNodeList.isEmpty()) {
                
                //User is dead
                game.loseLife();
                if (game.getLives() > 0) {
                    
                    // Clear out explosions, bulltes and asteroids
                    clearAllAsteroids();
                    clearAllBullets();

                    // Restart the level
                    creator.createShip(jpanel);
                    populateAsteroids(game);

                } else {
                    // Game Over
                    game.setStatusMessage("Game Over");
                    game.setGameIsOver();
                }
            }
            
            // Check if all asteroids are gone then go to next level
            if (asteroidCollisionNodeList != null && asteroidCollisionNodeList.isEmpty()) {
                clearAllBullets();
                game.increaseLevel();
                game.addPoints(1000);
                populateAsteroids(game);
            }

        } else {
            // New game
            creator.createGame(jpanel);
        }
        
    }
    
    private void populateAsteroids(Game game) {
        for (int i = 0; i < (5 * game.getLevel()); i++) { creator.createAsteroid(); }
    }
    
    private void clearAllBullets() {
        if (bulletCollisionNodeList != null) {
            for (int j = 0; j < bulletCollisionNodeList.size(); j++) {
                remover.markEntityForRemoval(((BulletCollisionNode)bulletCollisionNodeList.get(j)).entity );
            }
            remover.removeMarkedEntities();
        }
    }
    
    private void clearAllAsteroids() {
        if (asteroidCollisionNodeList != null) {
            for (int i = 0; i < asteroidCollisionNodeList.size(); i++) {
                remover.markEntityForRemoval(((AsteroidCollisionNode)asteroidCollisionNodeList.get(i)).entity);
            }
            remover.removeMarkedEntities();
        }
    }
    
}
