package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Random;

public class RandomlyMoving implements Behaviour<Movable> {

    @Override
    public void setUp(Movable  actor) {
        if (actor == null) {
            return;
        }

        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() -> {

                    Direction randomDirection = Direction.values()[new Random().nextInt(Direction.values().length)];
                    new Move<>(randomDirection, 2f).scheduleFor(actor);
                }),
                new Wait<>(1f)
            )
        ).scheduleFor(actor);
    }
}
