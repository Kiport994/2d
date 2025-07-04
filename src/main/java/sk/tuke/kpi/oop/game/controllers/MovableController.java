package sk.tuke.kpi.oop.game.controllers;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable actor;
    private Move<Movable> move;
    private Set<Direction> directions;

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    public MovableController(Movable actor) {
        this.actor = actor;
        this.directions = new HashSet<>();
    }

    private Direction combaineMove() {
        Direction direction = Direction.NONE;

        for(Direction d : directions) {
            direction = direction.combine(d);
        }

         return direction;
    }

    private void stopMove() {
        if (move != null && !move.isDone()) {
            move.stop();
        }

        move = null;
    }

    @Override
    public void keyPressed(Input.Key key) {
        Direction direction = keyDirectionMap.get(key);
        if (direction != null) {
            directions.add(direction);
            updateMove();
        }
    }

    @Override
    public void keyReleased(Input.Key key) {
        Direction direction = keyDirectionMap.get(key);
        if(direction != null) {
            directions.remove(direction);
            updateMove();
        }
    }

    private void updateMove() {
        stopMove();

        Direction direction = combaineMove();

        if(direction != Direction.NONE) {
            move = new Move<>(direction, Float.MAX_VALUE);
            move.scheduleFor(actor);
        } else {
            actor.stoppedMoving();
        }
    }
}
