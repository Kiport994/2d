package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

public interface Usable <A extends Actor> {
    void useWith(A actor);
    Class<A> getUsingActorClass();
}
