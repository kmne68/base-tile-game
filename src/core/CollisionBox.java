/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Rectangle;

/**
 *
 * @author kmne6
 */
public class CollisionBox {

  
  private Rectangle bounds;

  
  public CollisionBox(Rectangle bounds) {
    this.bounds = bounds;
  }
  
  
  public boolean collidesWith(CollisionBox other) {
    return bounds.intersects(other.getBounds());
  }
  

  public Rectangle getBounds() {
    return bounds;
  }
  
  
  public static CollisionBox of(Position position, Size size) {
    return new CollisionBox(
            new Rectangle(
              position.intX(),
              position.intY(),
              size.getWidth(),
              size.getHeight()                    
            )
    );
    
  }
  
  
}
