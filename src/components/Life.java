/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author CMMiller
 */
public class Life {
    
    private int lifeRemaining;
    private int standardLifeDecrement;

    public Life(int lifeRemaining, int standardLifeDecrement) {
        this.lifeRemaining = lifeRemaining;
        this.standardLifeDecrement = standardLifeDecrement;
    }

    public void decreaseLifeByStandardAmount() {
        this.lifeRemaining -= this.standardLifeDecrement;
    }
    public void changeLife(int change) {
        this.lifeRemaining += change;
    }
    
    public boolean isAlive() {
        return (this.lifeRemaining > 0);
    }
    
}
