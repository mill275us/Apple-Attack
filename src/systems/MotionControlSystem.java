/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.Engine;
import entitysystem.EntityCreator;
import entitysystem.KeyEnum;
import java.util.ArrayList;
import nodes.MotionControlNode;
import nodes.UserCollisionNode;
import components.User;

/**
 *
 * @author CMMiller
 */
public class MotionControlSystem implements ISystem {
    
    private EntityCreator creator;
    private ArrayList<MotionControlNode> motionControlNodeList;
    private ArrayList<UserCollisionNode> userCollisionNodeList;
    private Engine engine;

    public MotionControlSystem(EntityCreator creator) {
        this.creator = creator;
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        motionControlNodeList = this.engine.getMotionControlNodeList();
        userCollisionNodeList = this.engine.getUserCollisionNodeList();
    }

    @Override
    public void update(int time) {
        // Iterate through the nodes and adjust
        if (motionControlNodeList != null && userCollisionNodeList != null && !userCollisionNodeList.isEmpty()) {
            User user = (User)userCollisionNodeList.get(0).user;
            
            for (MotionControlNode node : motionControlNodeList) {

                double theta = node.position.getTheta(); 
                double vx = node.velocity.getX_velocity();
                double vy = node.velocity.getY_velocity();
                KeyEnum key = node.keyControl.getLastKey();
            
                if (key != null) {
                    node.keyControl.clearLastKey();
                    switch(key) {
                        case LEFT:
                            node.position.setTheta(theta - 0.5);
                            break;
                        case RIGHT:
                            node.position.setTheta(theta + 0.5);
                            break;
                        case UP:
                            node.velocity.setX_velocity(vx + (1 * Math.cos(theta)));
                            node.velocity.setY_velocity(vy + (1 * Math.sin(theta)));
                            break;
                        case DOWN:
                            node.velocity.setX_velocity(vx - (1 * Math.cos(theta)));
                            node.velocity.setY_velocity(vy - (1 * Math.sin(theta)));
                            break;
                        case SPACE:
                            user.getLastBulletAt();
                            
                            if (time - user.getLastBulletAt() >= 5) {
                                user.setLastBulletAt(time);
                                Double nx = node.position.getX() + 20 * Math.cos(theta);
                                Double ny = node.position.getY() + 20 * Math.sin(theta); 
                                creator.createBullet(nx.intValue(), ny.intValue(), theta);
                            }
                            break;        
                    }
                }
            }
        }
    }
}
