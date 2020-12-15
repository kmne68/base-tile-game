/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.menu.ui;

import core.Size;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

/**
 *
 * @author kmne6
 */
public class UIMainMenu extends VerticalContainer {
  
  
  public UIMainMenu(Size windowSize) {
    super(windowSize);
    
    alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
  
    addUIComponent(new UIText("C-YA"));
    addUIComponent(new UIButton("PLAY", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getGameSettings()))));
    addUIComponent(new UIButton("OPTIONS", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize))));
    addUIComponent(new UIButton("EXIT", (state) -> System.exit(0) ) );
    
  
  }  
  
}
