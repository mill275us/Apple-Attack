/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.Engine;
import entitysystem.Entity;
import entitysystem.EntityCreator;
import java.util.ArrayList;
import nodes.LifeNode;

/**
 *
 * @author CMMiller
 */
public class LifeSystem implements ISystem {
    
    private EntityCreator creator;
    private ArrayList<LifeNode> lifeNodeList;
    private Engine engine;
    private ArrayList<Entity> entitiesToBeRemoved;

    public LifeSystem(EntityCreator creator) {
        this.creator = creator;
        this.entitiesToBeRemoved = new ArrayList<Entity>();
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        this.lifeNodeList = engine.getLifeNodeList();
    }

    @Override
    public void update(int time) {
        // Iterate through each object and updates its position
        if (lifeNodeList != null) {
            for (LifeNode node : lifeNodeList) {
                
                node.life.decreaseLifeByStandardAmount();
                if ( ! node.life.isAlive() ) {
                    this.entitiesToBeRemoved.add(node.entity);
                }
            
            }
            
            // Remove all entities that need to be removed
            for (Entity entity : this.entitiesToBeRemoved) {
                this.creator.destroyEntity(entity);
            }
            this.entitiesToBeRemoved.clear();
        }
    }
    
    
    
}
