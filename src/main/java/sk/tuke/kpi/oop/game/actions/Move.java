package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.weapons.Bullet;

public class Move<A extends Movable> implements Action<A> {
    private A actor;
    private Direction direction;
    private float duration;
    private float et;
    private boolean start;
    private boolean done;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
        this.et = 0;
        this.start = false;
        this.done = false;
    }

    public Move(Direction direction) {

        this(direction, 0);
    }

    @Override
    public void setActor(A actor) {
        this.actor = actor;
    }

    @Override
    public A getActor() {
        return actor;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void reset() {
        this.et = 0;
        this.start = false;
        this.done = false;
    }

    public void stop() {
        if (!done) {
            actor.stoppedMoving();
            done = true;
        }
    }

    public void goDirection() {
        int dx = direction.getDx() * actor.getSpeed();
        int dy = direction.getDy() * actor.getSpeed();

        actor.setPosition(actor.getPosX() + dx, actor.getPosY() + dy);
    }

    public void checkGoDirection() {

        if (duration > 0 && (et >= duration || Math.abs(et - duration) < 1e-5)) {
            actor.stoppedMoving();
            done = true;
        }

        if (duration == 0) {
            actor.stoppedMoving();
            done = true;
        }
    }

    @Override
    public void execute(float deltaTime) {
        if (actor == null || direction == null || isDone()) {
            done = true;
            return;
        }

        if (!start) {
            actor.startedMoving(direction);
            start = true;
        }

        Scene scene = actor.getScene();
        if (scene == null) {
            done = true;
            return;
        }

        int currentX = actor.getPosX();
        int currentY = actor.getPosY();

        goDirection();

        if (scene.getMap().intersectsWithWall(actor)) {

            actor.collidedWithWall();
            actor.setPosition(currentX, currentY);


            done = true;

            actor.stoppedMoving();
        } else if (actor instanceof Bullet) {
            Bullet bullet = (Bullet) actor;
            bullet.collidedWithAlive();
        }

        et += deltaTime;

        checkGoDirection();
    }


}
