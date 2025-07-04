package sk.tuke.kpi.oop.game.actions;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private final int step;

    public PerpetualReactorHeating(int step) {
        this.step = step;
    }

    @Override
    public void execute(float deltatime) {
        Reactor reactor = getActor();
        if(reactor != null) {
            reactor.increaseTemperature(step);
        }
    }
}
