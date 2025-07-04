package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible {

    public Hammer() {
        super(1);
        Animation hammerAnimation = new Animation("sprites/hammer.png");
        setAnimation(hammerAnimation);
    }

    @Override
    public void useWith(Repairable repairable) {
        if (repairable != null) {
            boolean repaired = repairable.repair();
            if (repaired) {
                use();
            }
        }
    }

    @Override
    protected void useDelete() {
        deleteHammer();
    }

    public  void deleteHammer() {
        if(getScene() != null) {
            getScene().removeActor(this);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
