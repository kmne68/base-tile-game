/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;

/**
 *
 * @author kmne6
 */
public class GameState extends State {
  

  public GameState(Size windowSize, Input input) {
    super(windowSize, input);    
    
    Player player = new Player(new PlayerController(input), spriteLibrary );
    gameObjects.add(player);    
    gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    camera.focusOn(player);
    
  }
  
}
