/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import controller.PlayerController;
import core.Size;
import entity.GameObject;
import entity.Player;
import gfx.SpriteLibrary;
import input.Input;
import java.util.ArrayList;
import java.util.List;
import map.GameMap;

/**
 *
 * @author kmne6
 */
public abstract class State {  
  
  protected GameMap gameMap;
  protected List<GameObject> gameObjects;
  protected SpriteLibrary spriteLibrary;
  protected Input input;
  
  
  public State(Input input) {
    
    this.input = input;
  
    gameObjects = new ArrayList<GameObject>();
    spriteLibrary = new SpriteLibrary();
    
  }
  
  
  public void update() {    
    
    gameObjects.forEach(gameOjbect -> gameOjbect.update());
    
  }
  
  
  public List<GameObject> getGameObjects() {
    
    return gameObjects;
  }
  
  
  public GameMap getGameMap() {
    
    return gameMap;
    
  }
  
}
