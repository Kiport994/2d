package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private boolean isRepaired = false;
    private Disposable flickerAction;

    public DefectiveLight() {
        super();
    }

    @Override
    public void toggle() {
        if (!isRepaired) {
            super.toggle();
        }
    }

    public void randomLight() {
        if (!isRepaired && (int) (Math.random() * 10) == 1) {
            toggle();
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        flickerAction = new Loop<>(new Invoke<>(this::randomLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if (!isRepaired) {
            isRepaired = true;

            turnOn();

            if (flickerAction != null) {
                flickerAction.dispose();
                flickerAction = null;
            }

            new ActionSequence<>(
                new Wait<>(10),
                new Invoke<>(this::finishRepair)
            ).scheduleFor(this);

            return true;
        }
        return false;
    }

    private void finishRepair() {
        isRepaired = false;
        turnOff();

        flickerAction = new Loop<>(new Invoke<>(this::randomLight)).scheduleFor(this);
    }
}
