/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import controller.GameController;
import display.Display;
import core.Size;
import game.settings.GameSettings;
import state.game.GameState;
import state.State;
import input.Input;
import state.menu.MenuState;

/**
 *
 * @author kmne6
 */
public class Game {
  
  public static int SPRITE_SIZE = 64;
  
  private Display display;
  private Input input;
  private State state;
  private GameSettings gameSettings;
  private GameController gameController;
  
  public Game(int width, int height) {
    
    input = new Input();
    display = new Display(width, height, input);
    gameSettings = new GameSettings(false);
    state = new MenuState(new Size(width, height), input, gameSettings);
    gameController = new GameController(input);
  }
  
  
  public void update() {
    
    state.update(this);
    gameController.update(this);
  }
  
  
  public void render() {
    
    display.render(state, gameSettings.isDebugMode());
  }

  
  public GameSettings getSettings() {
    return gameSettings;
  }

  public void enterState(State nextState) {
    
    state = nextState;
  }
  
  
}
