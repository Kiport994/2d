package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int ammo;
    private int maxAmmo;

    public Firearm(int ammo, int maxAmmo) {
        this.ammo = ammo;
        this.maxAmmo = maxAmmo;
    }

    public Firearm(int ammo) {
        this(ammo, ammo);
    }

    public int getAmmo() {
        return ammo;
    }

    public void reload(int newAmmo) {
        ammo = Math.min(newAmmo + ammo, maxAmmo);
    }

    protected abstract Fireable createBullet();

    public Fireable fire() {
        if (getAmmo() > 0) {
            ammo--;
            return createBullet();
        } else {
            return null;
        }
    }
}
