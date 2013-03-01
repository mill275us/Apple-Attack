/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import components.Position;
import components.Velocity;
import components.KeyControl;
import entitysystem.Entity;
/**
 *
 * @author CMMiller
 */
public class MotionControlNode extends Node {
    
    public Position position;
    public Velocity velocity;
    public KeyControl keyControl;
}
