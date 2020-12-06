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
import entity.SelectionCircle;
import entity.humanoid.Humanoid;
import entity.humanoid.action.Cough;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.Game;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
import input.Input;
import java.awt.Color;
import map.GameMap;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIContainer;
import ui.Spacing;
import ui.UIText;
import ui.VerticalContainer;

/**
 *
 * @author kmne6
 */
public class GameState extends State {
  

  public GameState(Size windowSize, Input input) {
    super(windowSize, input);    

    gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    initializeCharacters();
    initializeUI(windowSize);
  }
  
  
  private void initializeCharacters() {
    
    SelectionCircle circle = new SelectionCircle();
    Player player = new Player(new PlayerController(input), spriteLibrary, circle);
    gameObjects.add(player); // addAll() is a way to generalize the addition
    
    // switch between camera focus on player and NPC
    // camera.focusOn(npc);
    camera.focusOn(player);
    
    gameObjects.add(circle);
    
    initializeNPCs(200);
    makeNumberOfNPCsSick(10);
  }

  private void initializeNPCs(int numberOfNPCs) {
    
    for(int i = 0; i < numberOfNPCs; i++) {
      NPC npc = new NPC(new NPCController(), spriteLibrary);
      
      // Place NPC(s) at fixed position
      // npc.setPosition(new Position(3 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE) );
      
      // Place NPC(s) at random positions
      npc.setPosition(gameMap.getRandomPosition());
      // npc.addEffect(new Sick());   // Make all NPCs sick
      gameObjects.add(npc);
    }
    
  }

  private void initializeUI(Size windowSize) {
    
    /**
     * The following commented code is left as an example
     */
//    UIContainer container = new VerticalContainer(windowSize);
//    container.setPadding(new Spacing(5));
//    container.setBackgroundColor(new Color(0, 0, 0, 0 ) );
//    container.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.START));
//    
//    UIContainer containerEnd = new VerticalContainer(windowSize);
//    containerEnd.setPadding(new Spacing(5));
//    containerEnd.setBackgroundColor(new Color(0, 0, 0, 0 ) );
//    containerEnd.setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.END));
//    
//    
//    container.addUIComponent(new UIText("Welcome!"));
//    containerEnd.addUIComponent(new UIText("Farewell!"));
//    uiContainers.add(container);
//    uiContainers.add(containerEnd);

    uiContainers.add(new UIGameTime(windowSize));
    uiContainers.add(new UISicknessStatistics(windowSize));
  }

  private void makeNumberOfNPCsSick(int initialSickNPCs) {
    
    getGameObjectsOfClass(NPC.class).stream()
            .limit(initialSickNPCs)
            .forEach(npc -> npc.addEffect(new Sick()));
  }
  
  
  public long getNumberOfSick() {
    
    return getGameObjectsOfClass(Humanoid.class).stream()
            .filter(humanoid -> humanoid.isAffectedBy(Sick.class) && !humanoid.isAffectedBy(Isolated.class))
            .count();
  }
  
    
  public long getNumberOfIsolated() {
    
    return getGameObjectsOfClass(Humanoid.class).stream()
            .filter(humanoid -> humanoid.isAffectedBy(Sick.class) && humanoid.isAffectedBy(Isolated.class))
            .count();
  }
  
  
  public long getNumberOfHealthy() {
    
    return getGameObjectsOfClass(Humanoid.class).stream()
            .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
            .count();
  }  
}
