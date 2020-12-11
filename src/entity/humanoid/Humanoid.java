/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.humanoid;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import state.State;
import gfx.SpriteLibrary;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kmne6
 */
public class Humanoid extends MovingEntity {

  protected List<Effect> effects;
  protected Optional<Action> action;

  public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
    super(entityController, spriteLibrary);

    effects = new ArrayList<>();
    action = Optional.empty();

    this.collisionBoxSize = new Size(16, 28);
    this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
    this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
  }

  @Override
  public void update(State state) {
    super.update(state);
    
    handleAction(state);
    effects.forEach(effect -> effect.update(state, this));

    cleanup();
  }

  private void cleanup() {
    List.copyOf(effects).stream()
            .filter(Effect::shouldDelete)
            .forEach(effects::remove);

    if (action.isPresent() && action.get().isDone()) {
      action = Optional.empty();
    }
  }

  protected void handleMotion() {

    if (action.isPresent()) {
      motion.stop(true, true);
    }
  }

  private void handleAction(State state) {

    if (action.isPresent()) {
      action.get().update(state, this);
    }
  }

  @Override
  protected void handleCollision(GameObject other) {
  }

  @Override
  protected String selectAnimation() {

    if (action.isPresent()) {
      return action.get().getAnimationName();
    } else if (motion.isMoving()) {
      return "walk";
    }

    return "stand";
  }

  public void addEffect(Effect effect) {

    effects.add(effect);
  }

  public void perform(Action action) {

    if(this.action.isPresent() && !this.action.get().isInterruptable()) {
      return;
    }
    this.action = Optional.of(action);
  }

  public void clearEffects() {
    effects.clear();
  }

  public boolean isAffectedBy(Class<?> aClass) {

    return effects.stream()
            .anyMatch(effect -> aClass.isInstance(effect));
  }

  public List<Effect> getEffects() {
    
    return effects;    
  }

}
