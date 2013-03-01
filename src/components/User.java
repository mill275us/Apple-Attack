/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author CMMiller
 */
public class User {
    
    private String state;
    private int lastBulletAt;

    public User(String state) {
        this.state = state;
        lastBulletAt = -5;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }

    public int getLastBulletAt() {
        return lastBulletAt;
    }

    public void setLastBulletAt(int lastBulletAt) {
        this.lastBulletAt = lastBulletAt;
    }
    
    
}
