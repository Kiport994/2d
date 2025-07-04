package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.Scene;

public class SmartCooler extends Cooler {

    private int coolingRate = 2;

    public SmartCooler(Reactor reactor) {
        super(reactor);
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        scheduleCooling();
    }

    private void scheduleCooling() {
        new Loop<>(new Invoke<>(this::smartCooler)).scheduleFor(this);
    }

    public void smartCooler() {
        Reactor reactor = getReactor();
        if (reactor != null) {
            int temperature = reactor.getTemperature();

            if (temperature > 2500) {
                turnOn();
            } else if (temperature < 1500) {
                turnOff();
            }
        }
    }

    @Override
    public void coolReactor() {
        Reactor reactor = getReactor();
        if (isOn() && reactor != null) {
            reactor.decreaseTemperature(coolingRate);
        }
    }
}
