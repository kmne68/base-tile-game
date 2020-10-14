/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author kmne6
 */
public class Alignment {

  
  public enum Position {
     START, CENTER, END
  }
  
  
  private final Position horizontal;
  private final Position vertical;
  
  
  public Alignment(Position horizontal, Position vertical) {
    
    this.horizontal = horizontal;
    this.vertical = vertical;
    
  }
  
  
  public Position getVertical() {
    return vertical;
  }

  public Position getHorizontal() {
    return horizontal;
  }
  
}
