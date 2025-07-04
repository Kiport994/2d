package sk.tuke.kpi.oop.game.weapons;


public class Gun extends Firearm {

    public Gun(int initialAmmo, int maxAmmo) {
        super(initialAmmo, maxAmmo);
    }

    @Override
    protected Fireable createBullet() {

        return new Bullet();
    }
}
