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
public class Vector2D {
  
    private double x;
    private double y;
    
    
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  
  public static Vector2D copyOf(Vector2D vector) {
    
    return new Vector2D(vector.getX(), vector.getY());
  }

  
  public static Vector2D directionBetweenPositions(Position start, Position end) {
    
    Vector2D direction = new Vector2D(start.getX() - end.getX(), start.getY() - end.getY());
    direction.normalize();
    
    return direction;
  }
  
  
  public static double dotProduct(Vector2D v1, Vector2D v2) {
    
    return v1.getX() * v2.getX() + v1.getY() * v2.getY();
  }
  
  
  public double length() {
    
    return Math.sqrt(x * x + y * y);
  }
  
  void multiply(double speed) {
    
    x *= speed;
    y *= speed;
  }
  
  /**
   * Normalize movement so that movement in all directions has the same unit
   * of distance.
   */
  public void normalize() {
    
    double length = length();
    x = x == 0 ? 0 : x / length;
    y = y == 0 ? 0 : y / length;
  }
  

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }


  
}
