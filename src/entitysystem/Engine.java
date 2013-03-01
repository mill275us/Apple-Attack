/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import systems.ISystem;
import systems.RenderSystem;
import nodes.*;
import components.*;

/**
 *
 * @author CMMiller
 */
public class Engine {
    
    private int counter;
    private ArrayList<ISystem> systems;
    private ArrayList<Entity> entities;
    private ArrayList<GameNode> gameNodeList;
    private ArrayList<RenderNode> renderNodeList;
    private ArrayList<MovementNode> movementNodeList;
    private ArrayList<LifeNode> lifeNodeList;
    private ArrayList<MotionControlNode> motionControlNodeList;
    private ArrayList<AsteroidCollisionNode> asteroidCollisionNodeList;
    private ArrayList<BulletCollisionNode> bulletCollisionNodeList;
    private ArrayList<UserCollisionNode> userCollisionNodeList;

    public Engine() {
        
        counter = 0;
        entities = new ArrayList<Entity>();
        systems = new ArrayList<ISystem>();
        gameNodeList = new ArrayList<GameNode>();
        renderNodeList = new ArrayList<RenderNode>();
        movementNodeList = new ArrayList<MovementNode>();
        lifeNodeList = new ArrayList<LifeNode>();
        motionControlNodeList = new ArrayList<MotionControlNode>();
        asteroidCollisionNodeList = new ArrayList<AsteroidCollisionNode>();
        bulletCollisionNodeList = new ArrayList<BulletCollisionNode>();
        userCollisionNodeList = new ArrayList<UserCollisionNode>();
    }
    
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        
        Iterator<AsteroidCollisionNode> iterAsteroid = asteroidCollisionNodeList.iterator();
        while (iterAsteroid.hasNext()) {
            if (iterAsteroid.next().entity == entity) {
                iterAsteroid.remove();
            }
        }
        
        Iterator<BulletCollisionNode> iterBullet = bulletCollisionNodeList.iterator();
        while (iterBullet.hasNext()) {
            if (iterBullet.next().entity == entity) {
                iterBullet.remove();
            }
        }
        
        Iterator<UserCollisionNode> iterUser = userCollisionNodeList.iterator();
        while (iterUser.hasNext()) {
            if (iterUser.next().entity == entity) {
                iterUser.remove();
            }
        }
        
        Iterator<GameNode> iterGame = gameNodeList.iterator();
        while (iterGame.hasNext()) {
            if (iterGame.next().entity == entity) {
                iterGame.remove();
            }
        }
        
        Iterator<RenderNode> iterRender = renderNodeList.iterator();
        while (iterRender.hasNext()) {
            if (iterRender.next().entity == entity) {
                iterRender.remove();
            }
        }
        
        Iterator<MovementNode> iterMove = movementNodeList.iterator();
        while (iterMove.hasNext()) {
            if (iterMove.next().entity == entity) {
                iterMove.remove();
            }
        }
        
        Iterator<LifeNode> iterLife = lifeNodeList.iterator();
        while (iterLife.hasNext()) {
            if (iterLife.next().entity == entity) {
                iterLife.remove();
            }
        }
        
        Iterator<MotionControlNode> iterMotionControl = motionControlNodeList.iterator();
        while (iterMotionControl.hasNext()) {
            if (iterMotionControl.next().entity == entity) {
                iterMotionControl.remove();
            }
        }
        
        // Update all the systems
        updateSystemsNodeLists();

    }
    
     
    public void addEntity(Entity entity) {
        entities.add(entity);
        
        // Check if we need a RenderNode
        if (entity.getComponents().containsKey("components.Position") && 
            entity.getComponents().containsKey("components.Display") ) {
            
            RenderNode renderNode = new RenderNode();
            renderNode.display = (Display)entity.get("components.Display");
            renderNode.position = (Position)entity.get("components.Position");
            renderNode.entity = entity;
            renderNodeList.add(renderNode);
        }
        // Check if we need a movement node
        if (entity.getComponents().containsKey("components.Position") && 
            entity.getComponents().containsKey("components.Velocity") ) {
            
            MovementNode movementNode = new MovementNode();
            movementNode.position = (Position)entity.get("components.Position");
            movementNode.velocity = (Velocity)entity.get("components.Velocity");
            movementNode.entity = entity;
            movementNodeList.add(movementNode);
        }
        // Check if we need a motion control node
        if (entity.getComponents().containsKey("components.Position") && 
            entity.getComponents().containsKey("components.Velocity") &&
            entity.getComponents().containsKey("components.KeyControl")    ) {
            
            MotionControlNode motionControlNode = new MotionControlNode();
            motionControlNode.position = (Position)entity.get("components.Position");
            motionControlNode.velocity = (Velocity)entity.get("components.Velocity");
            motionControlNode.keyControl = (KeyControl)entity.get("components.KeyControl");
            motionControlNode.entity = entity;
            motionControlNodeList.add(motionControlNode);
        }  

        // Check if we need a asteroid-collision node
        if (entity.getComponents().containsKey("components.Position") &&
            entity.getComponents().containsKey("components.Asteroid")    ) {
            
            AsteroidCollisionNode asteroidCollisionNode = new AsteroidCollisionNode();
            asteroidCollisionNode.position = (Position)entity.get("components.Position");
            asteroidCollisionNode.asteroid = (Asteroid)entity.get("components.Asteroid");
            asteroidCollisionNode.entity = entity;
            asteroidCollisionNodeList.add(asteroidCollisionNode);
        }  
        // Check if we need a bullet-collision node
        if (entity.getComponents().containsKey("components.Position") &&
            entity.getComponents().containsKey("components.Bullet")    ) {
            
            BulletCollisionNode bulletCollisionNode = new BulletCollisionNode();
            bulletCollisionNode.position = (Position)entity.get("components.Position");
            bulletCollisionNode.bullet= (Bullet)entity.get("components.Bullet");
            bulletCollisionNode.entity = entity;
            bulletCollisionNodeList.add(bulletCollisionNode);
        }
        // Check if we need a user-collision node
        if (entity.getComponents().containsKey("components.Position") &&
            entity.getComponents().containsKey("components.User")    ) {
            
            UserCollisionNode userCollisionNode = new UserCollisionNode();
            userCollisionNode.position = (Position)entity.get("components.Position");
            userCollisionNode.user = (User)entity.get("components.User");
            userCollisionNode.entity = entity;
            userCollisionNodeList.add(userCollisionNode);
        }
        // Check for life node
        if (entity.getComponents().containsKey("components.Life")  ) {
            
            LifeNode lifeNode = new LifeNode();
            lifeNode.life = (Life)entity.get("components.Life");
            lifeNode.entity = entity;
            lifeNodeList.add(lifeNode);
        }  
        // Check for game nodes
        if (entity.getComponents().containsKey("components.Game")  ) {
            
            GameNode gameNode = new GameNode();
            gameNode.game = (Game)entity.get("components.Game");
            gameNode.entity = entity;
            gameNodeList.add(gameNode);
        }
        
        // Check for other nodes
        
        // Update all the systems
        updateSystemsNodeLists();
    }
    
    private void updateSystemsNodeLists() {
        for (ISystem system : systems) {
            system.addToEngine(this);
        }
    }

    public void addSystem(ISystem system) {
        systems.add(system);
        system.addToEngine(this);
    }
    
    public void update(Graphics2D g2, ImageObserver observer) {
        counter++;
        for (ISystem system : systems) {
            
            if ((system.getClass().getName()).equals("systems.RenderSystem")) {
                ((RenderSystem)system).setG2(g2);
                ((RenderSystem)system).setObserver(observer);
                system.update(counter);
            } else {
                system.update(counter);
            }
            
        }
    }

    // Getters
    public ArrayList<AsteroidCollisionNode> getAsteroidCollisionNodeList() {
        return asteroidCollisionNodeList;
    }

    public ArrayList<BulletCollisionNode> getBulletCollisionNodeList() {
        return bulletCollisionNodeList;
    }

    public ArrayList<UserCollisionNode> getUserCollisionNodeList() {
        return userCollisionNodeList;
    }

    public ArrayList<GameNode> getGameNodeList() {
        return gameNodeList;
    }

    public ArrayList<MovementNode> getMovementNodeList() {
        return movementNodeList;
    }

    public ArrayList<LifeNode> getLifeNodeList() {
        return lifeNodeList;
    }
    
    public ArrayList<RenderNode> getRenderNodeList() {
        return renderNodeList;
    }
    
    public ArrayList<MotionControlNode> getMotionControlNodeList() {
        return motionControlNodeList;
    }
    
    
    
    
}
