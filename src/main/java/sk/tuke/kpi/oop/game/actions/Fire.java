package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.weapons.Fireable;


public class Fire<A extends Armed> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {

        A attacker = getActor();

        if (attacker == null || attacker.getFirearm() == null) {
            setDone(true);
            return;
        }

        Fireable bullet = attacker.getFirearm().fire();
        if (bullet == null) {

            setDone(true);
            return;
        }

        Scene scene = attacker.getScene();
        if (scene == null) {
            setDone(true);
            return;
        }

        scene.addActor((Actor) bullet, attacker.getPosX(), attacker.getPosY());

        float angle = attacker.getAnimation().getRotation();
        Direction direction = Direction.fromAngle(angle);

        new Move<Movable>(direction).scheduleFor((Movable) bullet);

        setDone(true);
    }
}

