/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author kmne6
 */
public class Position {
  
  // units are pixels
  private double x;
  private double y;
  
  
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  
  public int intX() {
    
    return (int) Math.round(x);
    
  }
  
  
  public int intY() {
    
    return (int) Math.round(y);
    
  }
  

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void apply(Movement movement) {
    
    Vector2D vector = movement.getVector();
    x += vector.getX();
    y += vector.getY();
    
  }

  
}
