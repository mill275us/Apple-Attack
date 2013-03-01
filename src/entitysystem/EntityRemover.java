/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import java.util.ArrayList;
/**
 *
 * @author CMMiller
 */
public class EntityRemover {
    
    private Engine engine;
    private ArrayList<Entity> entitiesToBeRemoved;

    public EntityRemover(Engine engine) {
        this.engine = engine;
        entitiesToBeRemoved = new ArrayList<Entity>();
    }
    
    public void markEntityForRemoval(Entity entity) {
        entitiesToBeRemoved.add(entity);
    }
    
    public void removeMarkedEntities() {
        for (Entity entity : entitiesToBeRemoved) {
            engine.removeEntity(entity);
        }
        entitiesToBeRemoved.clear();
    }
    
    
}
