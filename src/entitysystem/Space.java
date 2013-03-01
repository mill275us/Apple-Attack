/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import systems.*;

/**
 *
 * @author CMMiller
 */
public class Space extends JPanel implements ActionListener {
    
    private boolean paused = false;
    private Timer timer;
    
    private Engine engine;
    private EntityCreator creator;
    
    public Space() {

        setBackground(Color.black);
        
        // ES Initialization
        this.engine = new Engine();
        this.creator = new EntityCreator(engine);
//        this.creator.createGame(this);
        GameSystem gm = new GameSystem(this.creator);
        gm.setJPanel(this);
        
        this.engine.addSystem( gm );
        this.engine.addSystem( new MotionControlSystem(this.creator) );
        this.engine.addSystem( new MovementSystem(this.creator) );
        this.engine.addSystem( new CollisionSystem(this.creator) );
        this.engine.addSystem( new LifeSystem(this.creator) );
        this.engine.addSystem( new RenderSystem(this.creator) );
                
        setFocusable(true);
        
        // Main loop timer
        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;   
        
        this.engine.update(g2, this);

        g2.dispose();
        g.dispose();
    }

    
    
}
