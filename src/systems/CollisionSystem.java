/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import components.Game;
import entitysystem.Engine;
import entitysystem.EntityCreator;
import entitysystem.EntityRemover;
import java.util.ArrayList;
import nodes.UserCollisionNode;
import nodes.AsteroidCollisionNode;
import nodes.BulletCollisionNode;
import nodes.GameNode;
import entitysystem.Entity;
import components.Position;

/**
 *
 * @author CMMiller
 */
public class CollisionSystem implements ISystem {
    
    private Engine engine;
    private EntityCreator creator;
    private EntityRemover remover;
    private ArrayList<GameNode> gameNodeList;
    private ArrayList<UserCollisionNode> userCollisionNodeList;
    private ArrayList<BulletCollisionNode> bulletCollisionNodeList;
    private ArrayList<AsteroidCollisionNode> asteroidCollisionNodeList;

    public CollisionSystem(EntityCreator creator, EntityRemover remover) {
        this.creator = creator;
        this.remover = remover;
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

            // Check for collisions of all types  
            for (int i = 0; i < asteroidCollisionNodeList.size(); i++) {
                Entity asteroidEntity = asteroidCollisionNodeList.get(i).entity;

                // asteroid-asteroid
                for (int j = i + 1; j < asteroidCollisionNodeList.size(); j++) {                        
                    checkForCollision(asteroidEntity, asteroidCollisionNodeList.get(j).entity);
                }

                // ship-asteroid
                for (int j = 0; j < userCollisionNodeList.size(); j++) {
                    checkForCollision(asteroidEntity, userCollisionNodeList.get(j).entity);
                }

                // asteroid-bullet
                for (int j = 0; j < bulletCollisionNodeList.size(); j++) {

                    if (checkForCollision(asteroidEntity, bulletCollisionNodeList.get(j).entity) ) {
                        game.addPoints(100);
                    }

                }
            }
                                
            // ship-bullet


            // Remove all entities that need to be removed
            remover.removeMarkedEntities();
        }

    }   // End of update method
    
    private boolean checkForCollision(Entity entity1, Entity entity2) {
        int x = ((Position)entity1.get("components.Position")).getX();
        int y = ((Position)entity1.get("components.Position")).getY();      
        int x2 = ((Position)entity2.get("components.Position")).getX();
        int y2 = ((Position)entity2.get("components.Position")).getY();   

        if ( Math.abs(x2 - x) < 10 && Math.abs(y2 - y) < 10) {
            remover.markEntityForRemoval(entity1);
            remover.markEntityForRemoval(entity2);
            creator.createExplosion(x2, y2);
            return true;
        }
        return false;
    }
    
    
    
    
}
