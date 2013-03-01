/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import javax.swing.JFrame;
/**
 *
 * @author CMMiller
 */

public class EntitySystem extends JFrame {

    public EntitySystem() {
        add(new Space());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setTitle("Apple Attack");
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EntitySystem();
    }
}
