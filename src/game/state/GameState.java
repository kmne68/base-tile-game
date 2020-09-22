/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import game.Game;
import input.Input;
import java.util.List;
import map.GameMap;

/**
 *
 * @author kmne6
 */
public class GameState extends State {
  

  public GameState(Size windowSize, Input input) {
    super(windowSize, input);    

    gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    initializeCharacters();
  }
  
  
  private void initializeCharacters() {
    
    Player player = new Player(new PlayerController(input), spriteLibrary );
    NPC npc = new NPC(new NPCController(), spriteLibrary);
    npc.setPosition(new Position(3 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE) );
    gameObjects.addAll(List.of(player, npc)); // addAll() is a way to generalize the addition
    camera.focusOn(player);
  }
}
