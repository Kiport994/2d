package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Alive, Enemy, Movable {

    private int speed;
    private Health actualHealth;
    private Behaviour<? super Alien> actualBehaviour;

    public Alien(int health, Behaviour<? super Alien> behaviour) {
        this.actualHealth = new Health(health);
        this.actualBehaviour = behaviour;
        this.speed = 2;
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public Alien() {
        this(100, null);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Health getHealth() {
        return actualHealth;
    }

    @Override
    public void startedMoving(Direction direction) {
        float angle = direction.getAngle();
        getAnimation().setRotation(angle);
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().pause();
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        setUpBehaviour();
        scheduleDamageLoop(scene);
    }

    private void setUpBehaviour() {
        if (actualBehaviour != null) {
            actualBehaviour.setUp(this);
        }
    }

    private void scheduleDamageLoop(Scene scene) {
        new Loop<>(new Invoke<>(() -> applyDamageToCollidingActors(scene)))
            .scheduleFor(this);
    }

    private void applyDamageToCollidingActors(Scene scene) {
        for (Actor actor : scene.getActors()) {
            if (actor instanceof Alive && !(actor instanceof Enemy) && this.intersects(actor)) {
                Alive aliveActor = (Alive) actor;
                aliveActor.getHealth().drain(1);
            }
        }
    }
}
