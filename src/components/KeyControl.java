/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import entitysystem.KeyEnum;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author CMMiller
 */
public class KeyControl extends KeyAdapter {
    
    private KeyEnum lastKey;
        
    @Override
    public void keyPressed(KeyEvent e) {
            
        int key = e.getKeyCode();
        if ( key == KeyEvent.VK_LEFT)   {   this.lastKey = KeyEnum.LEFT;    }
        if ( key == KeyEvent.VK_RIGHT ) {   this.lastKey = KeyEnum.RIGHT;   }
        if ( key == KeyEvent.VK_UP )    {   this.lastKey = KeyEnum.UP;      }
        if ( key == KeyEvent.VK_DOWN )  {   this.lastKey = KeyEnum.DOWN;    }
        if ( key == KeyEvent.VK_SPACE)  {   this.lastKey = KeyEnum.SPACE;   }
    }
    
    public void clearLastKey() {
        this.lastKey = null;
    }

    public KeyEnum getLastKey() {
        return lastKey;
    }
    
    
}
