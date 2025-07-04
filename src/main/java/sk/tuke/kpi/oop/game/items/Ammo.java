package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public class Ammo extends AbstractActor implements Usable<Armed> {
    private int ammoAmount;
    public Ammo() {

        Animation ammo = new Animation("sprites/ammo.png");
        setAnimation(ammo);

        this.ammoAmount = 20;
    }

    @Override
    public void useWith(Armed ripley) {
        if (ripley == null) {
            return;
        }
        Firearm firearm = ripley.getFirearm();
        if (firearm != null) {
            firearm.reload(ammoAmount);

            Scene scene = getScene();
            if (scene != null) {
                scene.removeActor(this);
            }
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
