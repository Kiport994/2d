package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.Actor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A>{
    private int remainingUses;

    public BreakableTool(int initialUses) {
        this.remainingUses = initialUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    protected void setRemainingUses(int uses) {
        this.remainingUses = uses;
    }

    public void use() {
        if(remainingUses > 0) {
            remainingUses--;
            if (remainingUses == 0) {
                removeFromScene();
            }
        }
    }

    private void removeFromScene() {
        this.getScene().removeActor(this);
    }

    @Override
    public void useWith(A actor) {
        use();
    }

    protected void useDelete() {
        if (getScene() != null) {
            getScene().removeActor(this);
        }
    }
}
