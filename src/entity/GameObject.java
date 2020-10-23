/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;
import java.awt.Image;

/**
 *
 * @author kmne6
 */
public abstract class GameObject {
  
  protected Position position;
  protected Size size;
  private GameObject parent;
  
  
  public GameObject() {
    
    position = new Position(50.0, 50.0);
    size = new Size(50, 50);
  }
  
  
  public abstract void update(State state);
  public abstract Image getSprite();
  public abstract CollisionBox getCollisionBox();
  
  
  public boolean collidesWith(GameObject other) {
    
    return getCollisionBox().collidesWith(other.getCollisionBox());
  }
  

  public Position getPosition() {
    
    Position finalPosition = Position.copyOf(position);
    
    if(parent != null) {
      finalPosition.add(parent.getPosition());
    }
    
    return finalPosition;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public void setParent(GameObject parent) {
    this.parent = parent;
  }
  
  
  
  
}
