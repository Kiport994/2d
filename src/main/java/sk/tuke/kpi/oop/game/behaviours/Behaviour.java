package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;

public interface Behaviour<A extends Actor> {
    void setUp(A actor);
}
