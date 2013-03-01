/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import entitysystem.Engine;
import nodes.RenderNode;
import nodes.GameNode;
import components.Game;
import entitysystem.EntityCreator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author CMMiller
 */
public class RenderSystem implements ISystem {
    
    private EntityCreator creator;
    private Graphics2D g2;
    private ImageObserver observer;
    private ArrayList<RenderNode> renderNodeList;
    private ArrayList<GameNode> gameNodeList;
    private Engine engine;

    public RenderSystem(EntityCreator creator) {
        this.creator = creator;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public void setObserver(ImageObserver observer) {
        this.observer = observer;
    }

    @Override
    public void addToEngine(Engine engine) {
        this.engine = engine;
        
        this.renderNodeList = this.engine.getRenderNodeList();
        this.gameNodeList = this.engine.getGameNodeList();
    }
    
    @Override
    public void update(int Time) {
        
        g2.setColor(Color.white);
        int score = 0;
        int highScore = 0;
        int level = 0;
        int lives = 0;
        
        if (this.gameNodeList != null && !this.gameNodeList.isEmpty()) {
            Game game = (Game)this.gameNodeList.get(0).entity.get("components.Game");
            level = game.getLevel();
            score = game.getScore();
            lives = game.getLives();
            highScore = game.getHighScore();
            
            if (game.getStatusMessage() != null) {
                g2.drawString(game.getStatusMessage(), 200, 280);
                lives = 0;
            }
        }
        
        g2.drawString("Score: " + score, 50, 30);
        g2.drawString("High Score: " + highScore, 200, 30);
        g2.drawString("Level: " + level, 400, 30);
        
        // Render lives images
        ImageIcon iic = new ImageIcon(this.getClass().getResource("/resources/ship.png"));
        AffineTransform originalTransform = this.g2.getTransform();
        
        for (int i = 1; i < lives; i++) {
        this.g2.translate(20 * i, 550);
        this.g2.rotate(-1.5707);
        this.g2.drawImage(iic.getImage(), -10, -10, this.observer);
        this.g2.setTransform(originalTransform);  
        }
        
        // Iterate through and render each node
        if (renderNodeList != null) {
            for (RenderNode node : renderNodeList) {

                int x = node.position.getX(); 
                int y = node.position.getY(); 
                double theta = node.position.getTheta(); 
                Image image = node.display.getImage(); 

//                AffineTransform originalTransform = this.g2.getTransform();
                this.g2.translate(x, y);
                this.g2.rotate(theta);
                this.g2.drawImage(image, -10, -10, this.observer);
                this.g2.setTransform(originalTransform);   
            }
        }
    }
    
    
}
