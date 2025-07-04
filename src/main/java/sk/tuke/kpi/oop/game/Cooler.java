package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class Cooler extends AbstractActor implements Switchable{

    private Animation fanСooler;

    private boolean isOn;
    private Reactor reactor;
    public Cooler(Reactor reactor) {
        this.reactor = reactor;

        fanСooler = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP);
        setAnimation(fanСooler);
        fanСooler.stop();

        isOn = false;
    }

    @Override
    public void turnOn(){
        isOn = true;
        fanСooler.play();
    }

    public Reactor getReactor() {
        return reactor;
    }

    @Override
    public void turnOff(){
        isOn = false;
        fanСooler.stop();
    }

    @Override
    public boolean isOn(){
        return isOn;
    }

    public void setReactor(Reactor reactor){
        this.reactor = reactor;
    }

    public void coolReactor() {
        if(isOn && reactor != null) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
