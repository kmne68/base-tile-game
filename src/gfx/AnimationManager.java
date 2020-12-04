/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class AnimationManager {
    private SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;
    private boolean looping;
    
    private Graphics graphics;
    private Image image;
    private ImageObserver observer;

    public AnimationManager(SpriteSet spriteSet) {
        this(spriteSet, true);
    }

    public AnimationManager(SpriteSet spriteSet, boolean looping) {
      
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        this.looping = looping;
        currentAnimationName = "";
        playAnimation("stand");
    }  
    

    public Image getSprite() {
    //  currentAnimationSheet

      System.out.println("currentAnimationName: " + currentAnimationName + "\n" +
                         "frameIndex: " + frameIndex + "\n" +
                         "SPRITE_SIZE: " + Game.SPRITE_SIZE + "\n" +
                         "directionIndex: " + directionIndex + "\n" + 
                         "--------------\n" +
                         "frameIndex * Game.SPRITE_SIZE: " + frameIndex * Game.SPRITE_SIZE + "\n" +
                         "directionIndex * Game.SPRITE_SIZE: " + directionIndex * Game.SPRITE_SIZE + "\n" +
                         "Game.SPRITE_SIZE: " + Game.SPRITE_SIZE + 
                         "----------------------------------------------------------------\n\n\n" );
      

      System.out.println("Current animation sheet: height: " + currentAnimationSheet.getHeight() + " width: " + currentAnimationSheet.getWidth());
      System.out.println("current animation sheet " + currentAnimationSheet.toString());
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

            int animationSize = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if(frameIndex >= animationSize) {
                frameIndex = looping ? 0 : animationSize - 1;
            }
        }
    }

    public void playAnimation(String name) {
        if(!name.equals(currentAnimationName)) {
            this.currentAnimationSheet = (BufferedImage) spriteSet.getOrGetDefault(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }
}
