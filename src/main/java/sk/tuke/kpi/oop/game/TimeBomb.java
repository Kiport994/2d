package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.When;

public class TimeBomb extends AbstractActor {
    private Animation active;
    private Animation explosion;
    private float count;
    private boolean activated;

    public TimeBomb(float countdownTime) {
        super("TimeBomb");
        this.count = countdownTime;
        Animation bomb = new Animation("sprites/bomb.png");
        this.active = new Animation("sprites/bomb_activated.png", 16, 16, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.explosion = new Animation("sprites/small_explosion.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(bomb);
        activated = false;
    }

    public void activate() {
        if (!activated) {
            activated = true;
            setAnimation(active);

            new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(this::decrementTime),
                    new Wait<>(1)
                )
            ).scheduleFor(this);

            new ActionSequence<>(
                new Wait<>(count),
                new Invoke<>(this::detonate)
            ).scheduleFor(this);
        }
    }

    private void decrementTime() {
        if (count > 0) {
            count -= 1.0f;
        }
    }

    public boolean isActivated() {
        return activated;
    }

    private void detonate() {
        setAnimation(explosion);
        explosion.play();

        new When<>(
            () -> explosion.getCurrentFrameIndex() >= explosion.getFrameCount() - 1,
            new Invoke<>(() -> {
                if (getScene() != null) {
                    getScene().removeActor(this);
                }
            })
        ).scheduleFor(this);
    }
}
