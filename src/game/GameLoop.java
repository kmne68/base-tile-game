/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Game;

/**
 *
 * @author kmne6
 */
public class GameLoop implements Runnable {

  public static final int UPDATES_PER_SECOND = 60;

  private Game game;

  private boolean running;
  private final double updateRate = 1.0d / UPDATES_PER_SECOND; // 60 updates per second

  // diagnostic stats
  private long nextStatTime;
  private int fps;
  private int ups;  // updates per second;

  public GameLoop(Game game) {

    this.game = game;

  }

  @Override
  public void run() {

    running = true;
    double accumulator = 0;
    long currentTime;
    long lastUpdate = System.currentTimeMillis();
    nextStatTime = System.currentTimeMillis() + 1000;   // set to 1 second from now

    while (running) {
      currentTime = System.currentTimeMillis();
      double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
      accumulator += lastRenderTimeInSeconds * game.getSettings().getGameSpeedMultiplier();
      lastUpdate = currentTime;

      // check whether there is anything new to render
      if (accumulator >= updateRate) {
        while (accumulator > updateRate) {
          update();
          accumulator -= updateRate;
        }
      }
      render();
      printStats();
    }
  }

  private void printStats() {
    if (System.currentTimeMillis() > nextStatTime) {
      System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
      fps = 0;
      ups = 0;
      nextStatTime = System.currentTimeMillis() + 1000;
    }
  }

  private void update() {
    game.update();
    ups++;
  }

  private void render() {

    game.render();
    fps++;
  }
}
