package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {

    public Wrench() {
        super(2);
        Animation wrenchAnimation = new Animation("sprites/wrench.png");
        setAnimation(wrenchAnimation);
    }

    public void useWith(DefectiveLight repairable) {
        if (getUses() > 0 && repairable != null && repairable.repair()) {
            use();
        }
    }

    public int getUses() {
        return super.getRemainingUses();
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass() {
        return DefectiveLight.class;
    }
}
