package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.gamelib.Scene;
import java.util.Set;
import java.util.HashSet;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
    private boolean isOn;

    private Set<EnergyConsumer> devices;

    private Animation normal_Animation;
    private Animation hot_reactor_Animation;
    private Animation broken_reactor_Animation;
    private Animation off_reactor_Animation;

    public Reactor() {

        temperature = 0;
        damage = 0;

        normal_Animation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        off_reactor_Animation = new Animation("sprites/reactor.png");
        hot_reactor_Animation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        broken_reactor_Animation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(off_reactor_Animation);

        this.devices = new HashSet<>();
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public int getTemperature() {
        return temperature;
    }
    public int getDamage() {
        return damage;
    }

    private int tempCount(int reactor) {
        int count = reactor;

        if(damage >= 33 && damage <= 66) {

            count = (int)Math.round(reactor * 1.5);

        } else if (damage > 66){
            count = (int)Math.round(reactor * 2);
        }

        return count;
    }

    private void damCount() {
        if(temperature > 2000 && temperature < 6000) {
            damage = (int)Math.floor((temperature - 2000) / 40);
        }
    }

    private void track() {
        if(temperature >= 6000 || damage == 100){
            damage = 100;
            isOn = false;
            updateDevice();
        }
    }

    public void increaseTemperature(int reactor) {

        if(!isOn || damage >= 100 || reactor < 0) {
            return;
        }

        int addTemp = tempCount(reactor);

        temperature += addTemp;

        damCount();
        track();

        updateDevice();
        updateAnimation();
    }

    public void decreaseTemperature(int decrement) {

        if(!isOn || damage >= 100 || decrement < 0) {
            return;
        } else if (damage >= 50) {
            temperature -= decrement / 2;
        } else{
            temperature -= decrement;
        }

        if (temperature < 0) {
            temperature = 0;
        }
        updateDevice();
        updateAnimation();
    }

    private void updateAnimation() {
        if (damage >= 100) {
            setAnimation(broken_reactor_Animation);
        } else if (temperature > 4000) {
            setAnimation(hot_reactor_Animation);
        } else if (!isOn) {
            setAnimation(off_reactor_Animation);
        } else {
            setAnimation(normal_Animation);
        }
    }

    @Override
    public void turnOn() {
        if (damage < 100 && temperature < 6000) {
            isOn  = true;
            updateDevice();
        }
        updateAnimation();
    }
    @Override
    public void turnOff() {
        isOn = false;
        if (damage == 100) {
            updateAnimation();
        } else {
            setAnimation(off_reactor_Animation);
        }
        updateDevice();
    }
    @Override
    public boolean isOn() {
        return isOn;
    }

    public void addDevice(EnergyConsumer add){
        if (add != null) {
            devices.add(add);
            updateDevice();
            add.setPowered(isOn && damage < 100);
        }
    }
    public void removeDevice(EnergyConsumer add){
        if (add != null) {
            add.setPowered(false);
            devices.remove(add);
        }
    }

    private void updateDevice() {
        boolean power = isOn && damage < 100;
        for (EnergyConsumer device : devices) {
            device.setPowered(power);
        }
    }

    @Override
    public boolean repair() {
        if (damage >= 100) {
            return false;
        }

        if (damage > 0 && damage < 100) {
            damage -= 50;
            if (damage < 0) {
                damage = 0;
            }

            int new_temperature = 2000 + (damage * 40);

            if (new_temperature < temperature) {
                temperature = new_temperature;
            }

            updateDevice();
            updateAnimation();

            return true;
        }
        return false;
    }

    public boolean extinguish() {
        if(temperature < 6000) {
            return false;
        }
        temperature = 4000;
        updateDevice();
        setAnimation(new Animation("sprites/reactor_extinguished.png"));
        return true;
    }
}
