/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import core.CollisionBox;
import game.state.State;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author kmne6
 */
public class DebugRenderer {  
  
  
  public void render(State state, Graphics graphics) {

    Camera camera = state.getCamera();
    
    state.getGameObjects().stream()
            .filter(gameObject -> camera.isInView(gameObject))
            .map(gameObject -> gameObject.getCollisionBox())
            .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));

  }
  
  
  private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
      
    graphics.setColor(Color.red);
    graphics.drawRect(
            (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
            (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
            (int) collisionBox.getBounds().getWidth(),
            (int) collisionBox.getBounds().getHeight()
    );
  }
        
}
