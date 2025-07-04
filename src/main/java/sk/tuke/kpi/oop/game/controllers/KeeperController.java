package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

public class KeeperController implements KeyboardListener {
    private Keeper keeper;

    public KeeperController(Keeper keeper) {
        this.keeper = keeper;
    }

    @Override
    public void keyPressed(Input.Key key) {
        Scene scene = keeper.getScene();
        if (scene == null) {
            return;
        }

        switch (key) {
            case ENTER:
                new Take<>().scheduleFor(keeper);
                break;

            case BACKSPACE:
                new Drop<>().scheduleFor(keeper);
                break;

            case S:
                new Shift<>().scheduleFor(keeper);
                break;

            case U:
                scene.getActors().stream()
                    .filter(actor -> actor instanceof Usable<?>)
                    .filter(keeper::intersects)
                    .findFirst()
                    .ifPresent(actor -> {
                        Usable<?> usable = (Usable<?>) actor;
                        new Use<>(usable).scheduleForIntersectingWith(keeper);
                    });
                break;


            case B:
                if(keeper.getBackpack().getSize() > 0) {
                    Actor actor = keeper.getBackpack().peek();
                    if(actor instanceof Usable) {
                        Usable<?> usable = (Usable<?>) actor;
                        new Use<> (usable).scheduleForIntersectingWith(keeper);

                    }
                }
                break;
            default:
                break;
        }
    }

}
