/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysystem;

import components.*;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author CMMiller
 */
public class EntityCreator {
    
    private Engine engine;

    public EntityCreator(Engine engine) {
        this.engine = engine;
    }
    
    public void createGame(JPanel jpanel) {
        Entity game = new Entity();
        Game gameComponent = new Game(3, 1, 0);
        game.add(gameComponent);
        this.createShip(jpanel);
        for (int i = 0; i < 5; i++) { this.createAsteroid(); }
        engine.addEntity(game);
    }
    
    public void createBullet(int xpos, int ypos, double theta) {
        Entity bullet = new Entity();
        Bullet bulletComponent = new Bullet();
        Position positionComponent = new Position(xpos, ypos, 0);
        Double x_velocity = 10 * Math.cos(theta);
        Double y_velocity = 10 * Math.sin(theta);
        Velocity velocityComponent = new Velocity(x_velocity.intValue(), y_velocity.intValue(), 0);
        ImageIcon iic = new ImageIcon(this.getClass().getResource("/resources/bullet.png"));
        Display displayComponent = new Display(iic.getImage());
        Life lifeComponent = new Life(120, 5);
        bullet.add(bulletComponent);
        bullet.add(positionComponent);
        bullet.add(displayComponent);
        bullet.add(lifeComponent);
        bullet.add(velocityComponent);
        this.engine.addEntity(bullet);           
    }
    
    public void createExplosion(int xpos, int ypos) {
        Entity explosion = new Entity();
        Position positionComponent = new Position(xpos, ypos, 0);
        ImageIcon iic = new ImageIcon(this.getClass().getResource("/resources/explosion.png"));
        Display displayComponent = new Display(iic.getImage());
        Life lifeComponent = new Life(120, 10);
        explosion.add(positionComponent);
        explosion.add(displayComponent);
        explosion.add(lifeComponent);
        this.engine.addEntity(explosion);              
    }
    
    public void createShip(JPanel jpanel) {
        Entity ship = new Entity();
        Position positionComponent = new Position(250, 300, 0);
        ImageIcon iic = new ImageIcon(this.getClass().getResource("/resources/ship.png"));
        Display displayComponent = new Display(iic.getImage());
        Velocity velocityComponent = new Velocity(0, 0, 0);
        User userComponent = new User("Alive");
        KeyControl keyControlComponent = new KeyControl();
        jpanel.addKeyListener(keyControlComponent);
        ship.add(positionComponent);
        ship.add(displayComponent);
        ship.add(velocityComponent);
        ship.add(keyControlComponent);
        ship.add(userComponent);
        this.engine.addEntity(ship);
    }
    
    public void createAsteroid() {
        Entity asteroid = new Entity();
        Asteroid asteroidComponent = new Asteroid();
        Random gen = new Random();
        Position positionComponent = new Position(gen.nextInt(500)+50, gen.nextInt(500)+50, 3*(gen.nextDouble()-0.5));
        ImageIcon iic = new ImageIcon(this.getClass().getResource("/resources/apple20.png"));
        Display displayComponent = new Display(iic.getImage());
        Velocity velocityComponent = new Velocity(gen.nextInt(5), gen.nextInt(5), 3*(gen.nextDouble()-0.5));
        asteroid.add(asteroidComponent);
        asteroid.add(positionComponent);
        asteroid.add(displayComponent);
        asteroid.add(velocityComponent);
        this.engine.addEntity(asteroid);        
    }
    
    public void destroyEntity(Entity entity) {
        this.engine.removeEntity(entity);
    }
}
