/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import controller.GameController;
import display.Display;
import entity.GameObject;
import entity.Player;
import controller.PlayerController;
import core.Size;
import game.settings.GameSettings;
import game.state.GameState;
import game.state.State;
import gfx.SpriteLibrary;
import input.Input;
//import java.awt.*;
//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kmne6
 */
public class Game {
  
  public static int SPRITE_SIZE = 64;
  
  private Display display;
  private Input input;
  private State state;
  private GameSettings settings;
  private GameController gameController;
  
  public Game(int width, int height) {
    
    input = new Input();
    display = new Display(width, height, input);
    state = new GameState(new Size(width, height), input);
    settings = new GameSettings(false);
    gameController = new GameController(input);
  }
  
  
  public void update() {
    
    state.update();
    gameController.update(this);
  }
  
  
  public void render() {
    
    display.render(state, settings.isDebugMode());
  }

  
  public GameSettings getSettings() {
    return settings;
  }
  
  
}
