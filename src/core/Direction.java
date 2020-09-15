/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author kmne6
 * 
 * Holds the directions described by the sprites in a sprite sheet. The first
 * row always faces south and subsequent rows are oriented in a clockwise 
 * direction from there. The second row faces southwest, the third row faces 
 * west the fourth row faces northwest and so on to the last row which faces
 * southeast.
 * 
 */
public enum Direction {
  
  South(0),
  Southwest(1),
  West(2),
  Northwest(3),
  North(4),
  Northeast(5),
  East(6),
  Southeast(7);
  
  private int animationRow;
  
  
  Direction(int animationRow) {

    this.animationRow = animationRow;
    
  }
  
  
  public static Direction fromMotion(Motion motion) {
    
    double x = motion.getVector().getX();
    double y = motion.getVector().getY();
    Direction direction = null;
    
    if(x == 0 && y > 0)
      direction = South;
    if(x < 0 && y > 0)
      direction = Southwest;
    if(x < 0 && y == 0)
      direction = West;
    if(x < 0 && y < 0)
      direction = Northwest;
    if(x == 0 && y < 0)
      direction = North;
    if(x > 0 && y < 0)
      direction = Northeast;
    if(x > 0 && y == 0)
      direction = East;
    if(x > 0 && y > 0)
      direction = Southeast;
    
    return direction;
    
  }
  
  
  public int getAnimationRow() {
    
    return animationRow;
    
  }
  
}
