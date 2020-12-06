/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import core.CollisionBox;
import entity.humanoid.Humanoid;
import game.state.State;
import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.Collectors;
import ui.UIText;

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
    
    drawEffects(state, graphics);

  }
  
  
  private void drawEffects(State state, Graphics graphics) {
    
    Camera camera = state.getCamera();
    
    state.getGameObjectsOfClass(Humanoid.class).stream()
            .forEach(humanoid -> {
              UIText effectsText = new UIText(humanoid.getEffects()
                      .stream().map(effect -> effect.getClass().getSimpleName())
              .collect(Collectors.joining(","))
              );
              effectsText.update(state);
              
              graphics.drawImage(effectsText.getSprite(), 
                      humanoid.getPosition().intX() - camera.getPosition().intX(),
                      humanoid.getPosition().intY() - camera.getPosition().intY(),
                      null);
              
            });
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
