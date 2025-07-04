package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.map.TiledSceneMap;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Actor> {
    private int count_use;

    public Locker() {
        Animation locker = new Animation("sprites/locker.png", 16,16);
        setAnimation(locker);
        this.count_use = 0;
    }

    @Override
    public void useWith(Actor actor) {
        if (count_use != 0) {
            System.out.println("Locker already used");
            return;
        }
        System.out.println("Using Locker for the first time");
        count_use++;
        Scene scene = actor.getScene();

        if (scene == null) {
            System.out.println("Scene is null");
            return;
        }

        Hammer hammer = new Hammer();
        scene.addActor(hammer, actor.getPosX(), actor.getPosY());
    }


    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
