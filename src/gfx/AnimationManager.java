/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import core.Direction;
import game.Game;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author kmne6
 */
public class AnimationManager {
  
  private SpriteSet spriteSet;
  private BufferedImage currentAnimationSheet;
  private int updatesPerFrame;
  private int currentFrameTime;
  private int frameIndex;
  private int directionIndex;
  
  
  public AnimationManager(SpriteSet spriteSet) {
    
    this.spriteSet = spriteSet;
    this.updatesPerFrame = 20;
    this.frameIndex = 0;
    this.currentFrameTime = 0;
    this.directionIndex = 0;
    
    playAnimation("stand");
    
  }
  
  
  public Image getSprite() {
    
    return currentAnimationSheet.getSubimage(
            frameIndex * Game.SPRITE_SIZE,
            directionIndex * Game.SPRITE_SIZE,
            Game.SPRITE_SIZE,
            Game.SPRITE_SIZE
    );
  }
  
  
  public void update(Direction direction) {
    
    currentFrameTime++;
    directionIndex = direction.getAnimationRow();
    
    if(currentFrameTime >= updatesPerFrame) {
      currentFrameTime = 0;
      frameIndex++;
      
      if(frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE ) {
        frameIndex = 0;
      }
      
    }
  }
  
  
  public void playAnimation(String name) {
    
    // we cast to a Buffered Image to take advantage of that class' getSubmimage method
    this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
  }
  
}
