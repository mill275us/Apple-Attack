/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.Engine;
import entitysystem.EntityCreator;
import nodes.MovementNode;

import java.util.ArrayList;

/**
 *
 * @author CMMiller
 */
public class MovementSystem implements ISystem {
    
    private EntityCreator creator;
    private ArrayList<MovementNode> movementNodeList;
    private Engine engine;

    public MovementSystem(EntityCreator creator) {
        this.creator = creator;
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        this.movementNodeList = this.engine.getMovementNodeList();
    }
    
    @Override
    public void update(int time) {
        // Iterate through each object and updates its position
        if (movementNodeList != null) {
            for (MovementNode node : movementNodeList) {

                int x = node.position.getX(); 
                int y = node.position.getY(); 
                double theta = node.position.getTheta(); 
                double vx = node.velocity.getX_velocity();
                double vy = node.velocity.getY_velocity();
                double vtheta = node.velocity.getAngular_velocity();

                x += vx;
                y += vy;
                theta += vtheta;

                if (x > 500) {x = 0;}
                if (x < 0) {x = 500;}
                if (y > 550) {y = 50;}
                if (y < 50) {y = 550;}

                node.position.setX(x);
                node.position.setY(y);
                node.position.setTheta(theta);

            }
        }
    }
 
    
}
