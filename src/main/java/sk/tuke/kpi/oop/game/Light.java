package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer{
    private Animation on_Light;
    private Animation off_Light;
    private boolean isOn;
    private boolean isPowered;

    public Light() {

        on_Light = new Animation("sprites/light_on.png", 16, 16);
        off_Light = new Animation("sprites/light_off.png", 16, 16);

        isOn = false;
        isPowered = false;

        setAnimation(off_Light);
    }

    @Override
    public void turnOn() {
        isOn = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        isOn = false;
        updateAnimation();
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    public void toggle() {
        isOn = !isOn;
        updateAnimation();
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
        updateAnimation();
    }

    private void updateAnimation() {
        if (isOn && isPowered) {
            setAnimation(on_Light);
        } else {
            setAnimation(off_Light);
        }
    }
}
