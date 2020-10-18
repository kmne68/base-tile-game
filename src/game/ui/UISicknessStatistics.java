/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ui;

import core.Size;
import entity.MovingEntity;
import entity.effect.Sick;
import game.state.State;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

/**
 *
 * @author kmne6
 */
public class UISicknessStatistics extends HorizontalContainer {

  private UIText numberOfSick;
  private UIText numberOfHealthy;

  public UISicknessStatistics(Size windowSize) {
    super(windowSize);

    this.numberOfSick = new UIText("");
    this.numberOfHealthy = new UIText("");

    UIContainer sickContainer = new VerticalContainer(windowSize);
    sickContainer.setPadding(new Spacing(0));
    sickContainer.addUIComponent(new UIText("SICK"));
    sickContainer.addUIComponent(numberOfSick);

    UIContainer healthyContainer = new VerticalContainer(windowSize);
    healthyContainer.setPadding(new Spacing(0));
    healthyContainer.addUIComponent(new UIText("HEALTHY"));
    healthyContainer.addUIComponent(numberOfHealthy);

    addUIComponent(healthyContainer);
    addUIComponent(sickContainer);

  }

  @Override
  public void update(State state) {
    super.update(state);

    long sickCount = state.getGameObjects().stream()
            .filter(gameObject -> gameObject instanceof MovingEntity)
            .map(gameObject -> (MovingEntity) gameObject)
            .filter(movingEntity -> movingEntity.isAffectedBy(Sick.class))
            .count();

    long healthyCount = state.getGameObjects().stream()
            .filter(gameObject -> gameObject instanceof MovingEntity)
            .map(gameObject -> (MovingEntity) gameObject)
            .filter(movingEntity -> !movingEntity.isAffectedBy(Sick.class))
            .count();
    
    numberOfSick.setText(String.valueOf(sickCount));
    numberOfHealthy.setText(String.valueOf(healthyCount));
  }

}
