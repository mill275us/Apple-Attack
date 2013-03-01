/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.Engine;
import entitysystem.Entity;
import entitysystem.EntityCreator;
import entitysystem.EntityRemover;
import java.util.ArrayList;
import nodes.LifeNode;

/**
 *
 * @author CMMiller
 */
public class LifeSystem implements ISystem {
    
    private EntityRemover remover;
    private ArrayList<LifeNode> lifeNodeList;
    private Engine engine;

    public LifeSystem(EntityRemover remover) {
        this.remover = remover;
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        lifeNodeList = this.engine.getLifeNodeList();
    }

    @Override
    public void update(int time) {
        // Iterate through each object and updates its position
        if (lifeNodeList != null) {
            for (LifeNode node : lifeNodeList) {
                
                node.life.decreaseLifeByStandardAmount();
                if ( ! node.life.isAlive() ) {
                    remover.markEntityForRemoval(node.entity);
                }
            
            }
            
            // Remove all entities that need to be removed
            remover.removeMarkedEntities();
        }
    }
    
    
    
}
