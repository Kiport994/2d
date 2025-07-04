package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.WindowSetup;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Alive, Movable, Keeper, Armed {

    private Backpack backpack;

    private int ammo;
    private int speed;

    private Health health;

    private Firearm gun;

    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);

    public Ripley() {
        super("Ellen");
        this.ammo = 50;

        this.gun = new Gun(20, 20);

        this.backpack = new Backpack("Ripley's backpack", 10);
        this.health = new Health(100);
        this.health.onFatigued(new Health.FatigueEffect() {
            @Override
            public void apply() {
                setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));

                Scene scene = getScene();
                if (scene != null) {
                    scene.getMessageBus().publish(RIPLEY_DIED, Ripley.this);
                    scene.cancelActions(Ripley.this);
                }
            }
        });
        this.speed = 1;
        Animation player = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(player);
        stoppedMoving();
    }
    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        float angle = direction.getAngle();
        getAnimation().setRotation(angle);
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().pause();
    }

    public void showRipleyState() {
        Scene scene = getScene();
        if (scene == null) return;

        Overlay overlay = scene.getGame().getOverlay();
        WindowSetup windowSetup = scene.getGame().getWindowSetup();

        int windowHeight = windowSetup.getHeight();
        int windowWidth = windowSetup.getWidth();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        int xTextPosEnergy = windowWidth / GameApplication.STATUS_LINE_OFFSET * 4;
        int xTextPosAmmo = windowWidth / GameApplication.STATUS_LINE_OFFSET * 10;

        overlay.drawText(" | Health: " + this.getHealth().getValue(), xTextPosEnergy, yTextPos);
        overlay.drawText(" | Ammo: " + this.getAmmo(), xTextPosAmmo, yTextPos);
    }

    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.gun = weapon;
    }
}
