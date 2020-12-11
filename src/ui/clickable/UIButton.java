/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.clickable;

import core.Size;
import state.State;
import java.awt.Color;
import java.awt.Image;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

/**
 *
 * @author kmne6
 */
public class UIButton extends UIClickable {
  
  
  private UIContainer container;
  private UIText label;
  
  private ClickAction clickAction;
  

  public UIButton(String label, ClickAction clickAction) {
    
    this.label = new UIText(label);
    this.clickAction = clickAction;
    
    container = new VerticalContainer(new Size(0, 0));
    container.addUIComponent(this.label);
    container.setFixedSize(new Size(150, 30));
    
  }
  
  
  @Override
  public void update(State state) {
    super.update(state);
    container.update(state);
    size = container.getSize();
    
    Color color = Color.GRAY;
    
    if(hasFocus) {
      color = Color.LIGHT_GRAY;
    }
    
    if(isPressed) {
      color = Color.DARK_GRAY;
    }
    
    container.setBackgroundColor(color);
  }
  

  protected void onClick(State state) {
    
    clickAction.execute(state);
  }
  

  @Override
  public Image getSprite() {
    
    return container.getSprite();
  }
  
}
