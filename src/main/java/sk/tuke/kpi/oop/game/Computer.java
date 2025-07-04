package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer{

    private Animation normalAnimation;
    private boolean isPowered;

    public Computer() {
        normalAnimation = new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
        isPowered = false;
        normalAnimation.stop();
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
        if (isPowered) {
            normalAnimation.play();
        } else {
            normalAnimation.stop();
        }
    }

    public int add(int a, int b) {
        if (!isPowered) {
            return 0;
        }
        return a + b;
    }
    public float add(float a, float b) {
        if (!isPowered) {
            return 0;
        }
        return a + b;
    }

    public int sub(int a, int b) {
        if (!isPowered) {
            return 0;
        }
        return a - b;
    }
    public float sub(float a, float b) {
        if (!isPowered) {
            return 0;
        }
        return a - b;
    }
}

