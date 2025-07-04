package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;


public class Ventilator extends AbstractActor implements Repairable {

    private Animation ventilator;

    private boolean repaired;

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("Ventilator Repaired", Ventilator.class);
    public Ventilator() {

        ventilator = new Animation("sprites/ventilator.png", 32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(ventilator);

        ventilator.stop();
        this.repaired = false;
    }

    @Override
    public boolean repair() {
        if (repaired) {
            return false;
        }

        ventilator.play();
        repaired = true;

        Scene scene = getScene();
        scene.getMessageBus().publish(VENTILATOR_REPAIRED, this);

        return true;
    }

}
