/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author CMMiller
 */
public class Entity {
    
    private Map<String, Object> components;

    public Entity() {
        this.components = new HashMap<String, Object>();
    }
    
    public void add(Object object) {
//        System.out.println("adding " +object.getClass().getName());
        this.components.put(object.getClass().getName(), object);
    }
    
    public void remove(String className) {
//        System.out.println("removinging " + className);
        this.components.remove(className);
    }
    
    public boolean hasComponent(String className) {
        return this.components.containsValue(className);
    }
    
    public Object get(String className) {
        return this.components.get(className);
    }

    public Map<String, Object> getComponents() {
        return components;
    }

   
    
    
}
