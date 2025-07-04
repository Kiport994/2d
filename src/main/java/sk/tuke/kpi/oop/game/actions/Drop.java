package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop <K extends Keeper> extends AbstractAction<K>  {

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

        Collectible item = (Collectible) keeper.getBackpack().peek();
        if(item != null) {
            keeper.getBackpack().remove(item);

            scene.addActor(item, keeper.getPosX(), keeper.getPosY());
        }
        setDone(true);
    }

}
