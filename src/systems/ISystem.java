/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.util.ArrayList;
import nodes.Node;
import entitysystem.Engine;
/**
 *
 * @author CMMiller
 */
public interface ISystem {
    
    public void addToEngine(Engine engine);    
    public void update(int time);
    
}
