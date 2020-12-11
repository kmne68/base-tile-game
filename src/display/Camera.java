/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.Game;
import state.State;
import java.awt.Rectangle;
import java.util.Optional;

/**
 *
 * @author kmne6
 */
public class Camera {
  
  private static final int SAFETY_SPACE = 2 * Game.SPRITE_SIZE;
  
  private Position position;
  private Size windowSize;
  
  private Rectangle viewBounds;
  
  private Optional<GameObject> objectWithFocus;
  
  
  public Camera(Size windowSize) {
    
    this.position = new Position(0, 0);
    this.windowSize = windowSize;
    this.objectWithFocus = Optional.empty();
    calculateViewBounds();
  }
  
  
  public void focusOn(GameObject object) {
    
    this.objectWithFocus = Optional.of(object);
  }
  
  
  public void update(State state) {
    
    if(objectWithFocus.isPresent()) {
      Position objectPosition = objectWithFocus.get().getPosition();
      
      // the centers the camera? based on window size--not sure about this
      this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2 );
      this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2 );
      
      clampWithinBounds(state);
      calculateViewBounds();
    }
  }

  public Position getPosition() {
    return position;
  }

  private void clampWithinBounds(State state) {
    
    if(position.getX() < 0 ) {
      position.setX(0);
    }
    
    if(position.getY() < 0) {
      position.setY(0);
    }
    
    if(position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()) {
      position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
    }
    
    if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()) {
      position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
    }
    
  }

  public boolean isInView(GameObject gameObject) {
    
    return viewBounds.intersects(
            gameObject.getPosition().intX(),
            gameObject.getPosition().intY(),
            gameObject.getSize().getWidth() + SAFETY_SPACE,
            gameObject.getSize().getHeight() + SAFETY_SPACE
    );
  }

  private void calculateViewBounds() {
    
    viewBounds = new Rectangle(
            position.intX(), 
            position.intY(), 
            windowSize.getWidth(), 
            windowSize.getHeight() );
  }

  public Size getWindowSize() {
    
    return windowSize;    
  }
  
  
}
