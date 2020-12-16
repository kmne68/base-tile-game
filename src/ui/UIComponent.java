/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Position;
import core.Size;
import state.State;
import java.awt.Image;

/**
 *
 * @author kmne6
 */
public abstract class UIComponent {
  
  protected Position relativePosition;
  protected Position absolutePosition;
  protected Size size;
  protected Spacing margin;
  protected Spacing padding;
  
  protected UIContainer parent;

  public UIComponent() {
    
    relativePosition = new Position(0, 0);
    absolutePosition = new Position(0, 0);
    size = new Size(1, 1);
    margin = new Spacing(0);
    padding = new Spacing(0);
    
  }
  
  
  public abstract Image getSprite();
  public abstract void update(State state);
  

  public Position getRelativePosition() {
    return relativePosition;
  }

  public void setRelativePosition(Position position) {
    this.relativePosition = position;
  }

  public Size getSize() {
    return size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public Spacing getMargin() {
    return margin;
  }

  public void setMargin(Spacing margin) {
    this.margin = margin;
  }

  public Spacing getPadding() {
    return padding;
  }

  public void setPadding(Spacing padding) {
    this.padding = padding;
  }

  public Position getAbsolutePosition() {
    return absolutePosition;
  }

  public void setAbsolutePosition(Position absolutePosition) {
    this.absolutePosition = absolutePosition;
  }

  public void setParent(UIContainer parent) {
    this.parent = parent;
  }
  
  
}
