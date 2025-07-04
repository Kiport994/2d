package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Take<K extends Keeper> extends AbstractAction<K> {

    @Override
    public void execute(float deltaTime) {
        K keeper = getActor();
        if(keeper == null) {
            setDone(true);
            return;
        }

        Scene scene = keeper.getScene();
        if(scene == null) {
            setDone(true);
            return;
        }

        Collectible item = null;

        for (Actor actor : scene.getActors()) {
            if (actor instanceof Collectible && keeper.intersects(actor)) {
                item = (Collectible) actor;
                break;
            }
        }

        if(item != null) {
            try {
                keeper.getBackpack().add(item);
                scene.removeActor(item);
            } catch (IllegalStateException ex) {
                Overlay overlay = scene.getGame().getOverlay();
                overlay.drawText(ex.getMessage(), 100, 100).showFor(2);
            }
        }

        setDone(true);
    }
}
