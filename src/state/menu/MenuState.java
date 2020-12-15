/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.menu;

import core.Size;
import game.settings.GameSettings;
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

  public MenuState(Size windowSize, Input input, GameSettings gameSettings) {
    super(windowSize, input, gameSettings);

    gameMap = new GameMap(new Size(20, 20), spriteLibrary);

    uiContainers.add(new UIMainMenu(windowSize));
    audioPlayer.playMusic("isobubbler.wav");
  }

  public void enterMenu(UIContainer container) {

    uiContainers.clear();
    uiContainers.add(container);

  }

}
