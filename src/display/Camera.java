/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.state.State;
import java.util.Optional;

/**
 *
 * @author kmne6
 */
public class Camera {
  
  private Position position;
  private Size windowSize;
  private Optional<GameObject> objectWithFocus;
  
  
  public Camera(Size windowSize) {
    
    this.position = new Position(0, 0);
    this.windowSize = windowSize;
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
  
  
}
