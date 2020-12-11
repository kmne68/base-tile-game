/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.menu;

import core.Size;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

/**
 *
 * @author kmne6
 */
public class MenuState extends State {

  public MenuState(Size windowSize, Input input) {
    super(windowSize, input);

    gameMap = new GameMap(new Size(20, 20), spriteLibrary);

    uiContainers.add(new UIMainMenu(windowSize));

  }

  public void enterMenu(UIContainer container) {

    uiContainers.clear();
    uiContainers.add(container);

  }

}
