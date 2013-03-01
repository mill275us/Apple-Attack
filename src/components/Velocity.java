/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author CMMiller
 */
public class Velocity {
    
    private double x_velocity;
    private double y_velocity;
    private double angular_velocity;

    public Velocity(double x_velocity, double y_velocity, double angular_velocity) {
        this.x_velocity = x_velocity;
        this.y_velocity = y_velocity;
        this.angular_velocity = angular_velocity;
    }

    public double getAngular_velocity() {
        return angular_velocity;
    }

    public void setAngular_velocity(double angular_velocity) {
        this.angular_velocity = angular_velocity;
    }

    public double getX_velocity() {
        return x_velocity;
    }

    public void setX_velocity(double x_velocity) {
        this.x_velocity = x_velocity;
    }

    public double getY_velocity() {
        return y_velocity;
    }

    public void setY_velocity(double y_velocity) {
        this.y_velocity = y_velocity;
    }
    
    
    
}
