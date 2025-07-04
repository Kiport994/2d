package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift <K extends Keeper> extends AbstractAction<K> {

    @Override
    public void execute(float deltaTime) {
        K keeper = getActor();
        if(keeper == null) {
            setDone(true);
            return;
        }

        keeper.getBackpack().shift();

        setDone(true);
    }
}
