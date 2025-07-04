package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Bullet extends AbstractActor implements Fireable {

    private int speed;

    public Bullet(Direction direction) {

        Animation bull = new Animation("sprites/bullet.png", 16,16);
        setAnimation(bull);

        this.speed = 4;
        startedMoving(direction);
    }

    public Bullet() {
        this(Direction.NORTH);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {

        getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void collidedWithWall() {

        Scene scene = getScene();
        if (scene != null) {
            scene.removeActor(this);
        }
    }

    @Override
    public void collidedWithAlive() {
        Scene scene = getScene();
        if (scene == null) return;

        scene.getActors().stream()
            .filter(actor -> actor instanceof Alive && this.intersects(actor))
            .findFirst()
            .ifPresent(actor -> {
                Alive alive = (Alive) actor;
                alive.getHealth().drain(50);
                scene.removeActor(this);
            });
    }


}
