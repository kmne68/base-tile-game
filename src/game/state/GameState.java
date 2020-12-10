/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
import input.Input;
import java.awt.Color;
import java.util.List;
import map.GameMap;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

/**
 *
 * @author kmne6
 */
public class GameState extends State {

  private List<Condition> victoryConditions;
  private List<Condition> defeatConditions;
  private boolean playing;

  public GameState(Size windowSize, Input input) {
    super(windowSize, input);

    gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    playing = true;

    initializeCharacters();
    initializeUI(windowSize);
    initializeConditions();
  }

  private void initializeCharacters() {

    SelectionCircle circle = new SelectionCircle();
    Player player = new Player(new PlayerController(input), spriteLibrary, circle);
    gameObjects.add(player); // addAll() is a way to generalize the addition

    // switch between camera focus on player and NPC
    // camera.focusOn(npc);
    camera.focusOn(player);

    gameObjects.add(circle);

    initializeNPCs(10);
    makeNumberOfNPCsSick(0);
  }

  private void initializeNPCs(int numberOfNPCs) {

    for (int i = 0; i < numberOfNPCs; i++) {
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

    return getGameObjectsOfClass(NPC.class).stream()
            .filter(npc -> npc.isAffectedBy(Sick.class) && !npc.isAffectedBy(Isolated.class))
            .count();
  }

  public long getNumberOfIsolated() {

    return getGameObjectsOfClass(NPC.class).stream()
            .filter(npc -> npc.isAffectedBy(Sick.class) && npc.isAffectedBy(Isolated.class))
            .count();
  }

  public long getNumberOfHealthy() {

    return getGameObjectsOfClass(NPC.class).stream()
            .filter(npc -> !npc.isAffectedBy(Sick.class))
            .count();
  }

  private void initializeConditions() {

    victoryConditions = List.of(() -> getNumberOfSick() == 0);
    defeatConditions = List.of(() -> (float) getNumberOfSick() / getNumberOfNPCs() > 0.25);

  }

  private long getNumberOfNPCs() {

    return getGameObjectsOfClass(NPC.class).size();
  }

  @Override
  public void update() {
    super.update();

    if (playing) {
      if (victoryConditions.stream().allMatch(Condition::isMet)) {
        win();
      }

      if (defeatConditions.stream().allMatch(Condition::isMet)) {
        lose();
      }

    }
  }

  private void win() {
    playing = false;

    VerticalContainer winContainer = new VerticalContainer(camera.getWindowSize());
    winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
    winContainer.setBackgroundColor(Color.DARK_GRAY);
    winContainer.addUIComponent(new UIButton("MENU", () -> System.out.println("MENU button pressed!") ) );
    winContainer.addUIComponent(new UIButton("OPTIONS", () -> System.out.println("OPTIONS button pressed!") ) );
    winContainer.addUIComponent(new UIButton("EXIT", () -> System.exit(0) ) );
    uiContainers.add(winContainer);
  }

  private void lose() {
    playing = false;

    VerticalContainer loseContainer = new VerticalContainer(camera.getWindowSize());
    loseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
    loseContainer.addUIComponent(new UIText("DEFEAT!"));
    uiContainers.add(loseContainer);

  }
}
