/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ui;

import core.Size;
import game.state.State;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIText;

/**
 *
 * @author kmne6
 */
public class UIGameTime extends HorizontalContainer {
  
  private UIText gameTime;
  
  public UIGameTime(Size windowSize) {
    super(windowSize);
    
    this.alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.START);
    this.gameTime = new UIText("");
    addUIComponent(gameTime);
  }
  
  @Override
  public void update(State state) {
    super.update(state);
    
    gameTime.setText(state.getTime().getFormattedTime());
    
  }
  
}
