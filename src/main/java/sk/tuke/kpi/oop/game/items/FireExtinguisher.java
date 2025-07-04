package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {

    public FireExtinguisher() {
        super(1);
        Animation fireExting = new Animation("sprites/extinguisher.png");

        setAnimation(fireExting);
    }

    @Override
    public void useWith(Reactor reactor) {
        if (reactor != null) {
            boolean extinguished = reactor.extinguish();
            if (extinguished) {
                use();
            }
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
