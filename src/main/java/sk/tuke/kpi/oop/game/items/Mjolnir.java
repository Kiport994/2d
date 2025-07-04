package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.Repairable;

public class Mjolnir extends Hammer {

    public Mjolnir() {
        super();
        setRemainingUses(4);
    }

    public void deleteMjolnir() {
        if(getScene() != null) {
            getScene().removeActor(this);
        }
    }

    @Override
    public void useWith(Repairable repairable) {
        if(repairable == null || getRemainingUses() <= 0) {
            return;
        }

        boolean repaired = repairable.repair();

        if (repaired) {
            use();
            if (getRemainingUses() == 0) {
                deleteMjolnir();
            }
        }
    }
}

