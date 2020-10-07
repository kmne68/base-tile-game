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
public class Spacing {
  
  private int top;
  private int right;
  private int bottom;
  private int left;
  
  
  public Spacing (int spacing) {
    this(spacing, spacing);
  }
          
    
  public Spacing(int horizontal, int vertical) {
    this(vertical, horizontal, vertical, horizontal);
  }

  public Spacing(int top, int right, int bottom, int left) {
    this.top = top;
    this.right = right;
    this.bottom = bottom;
    this.left = left;
  }

  public int getTop() {
    return top;
  }

  public int getRight() {
    return right;
  }

  public int getBottom() {
    return bottom;
  }

  public int getLeft() {
    return left;
  }

  
  public int getVertical() {
    return top + bottom;
  }
  
  public int getHorizontal() {
    return right + left;
  }
  
}
