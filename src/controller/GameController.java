/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import game.Game;
import game.state.State;
import input.Input;
import java.awt.event.KeyEvent;

/**
 *
 * @author kmne6
 */
public class GameController {
  
  private Input input;

  
  public GameController(Input input) {
    this.input = input;
  }
  
  
  public void update(Game game) {
    
    if(input.isPressed(KeyEvent.VK_F2)) {
      game.getSettings().toggleDebugMode();
    }
    
    if(input.isPressed(KeyEvent.VK_Y) ) {
      game.getSettings().increaseGameSpeed();
    }
    
    if(input.isPressed(KeyEvent.VK_H) ) {
      game.getSettings().decreaseGameSpeed();
    }
    
  }
  
  
}
