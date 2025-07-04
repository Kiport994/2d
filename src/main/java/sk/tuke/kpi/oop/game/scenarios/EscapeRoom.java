package sk.tuke.kpi.oop.game.scenarios;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.MotherAlien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class EscapeRoom implements SceneListener {
    private Ripley ripley;
    private Energy energy;

    private Ventilator ventilator;

    private Ammo ammo;

    private Disposable stop_energy;

    private Disposable reg_movable;
    private Disposable reg_keeper;

    @Override
    public void sceneInitialized(Scene scene) {

        ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley == null) {
            return;
        }

        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);

        reg_movable = scene.getInput().registerListener(movableController);
        reg_keeper = scene.getInput().registerListener(keeperController);

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, deadRipley -> {
            reg_movable.dispose();
            reg_keeper.dispose();
        });

        scene.follow(ripley);



        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
            stop_energy = new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(() -> {
                        if (ripley.getHealth().getValue() > 0) {
                            ripley.getHealth().drain(1);
                        }
                    }),
                    new Wait<>(1.0f)
                )
            ).scheduleOn(scene);
        });

        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, ventilator -> {
            stop_energy.dispose();
        });

        energy = scene.getFirstActorByType(Energy.class);
        if (energy != null) {
            new When<>(
                () -> ripley.intersects(energy),
                new Invoke<>(() -> new Use<>(energy).scheduleFor(ripley))
            ).scheduleOn(scene);
        }

        ammo = scene.getFirstActorByType(Ammo.class);
        if (ammo != null) {
            new When<>(
                () -> ripley.intersects(ammo),
                new Invoke<>(() -> new Use<>(ammo).scheduleFor(ripley))
            ).scheduleOn(scene);
        }

        scene.getGame().pushActorContainer(ripley.getBackpack());
    }

    @Override
    public void sceneUpdating(Scene scene) {
        if (ripley != null) {
            ripley.showRipleyState();
        }
    }

    public static class Factory implements ActorFactory {
        @Override
        public Actor create(String type, String name) {
            if (name == null) {
                return null;
            }

            switch (name) {
                case "energy":
                    return new Energy();
                case "ellen":
                    return new Ripley();
                case "ventilator":
                    return new Ventilator();
                case "front door":
                    return new LockedDoor("front door", Door.Orientation.VERTICAL);
                case "back door":
                    return new LockedDoor("back door", Door.Orientation.HORIZONTAL);
                case "exit door":
                    return new LockedDoor("exit door", Door.Orientation.VERTICAL);

                case "access card":
                    return new AccessCard();
                case "locker":
                    return new Locker();
                case "ammo":
                    return new Ammo();

                case "alien":
                    switch (type) {
                        case "waiting1":
                            return new Alien(
                                100,
                                new Observing<>(
                                    Door.DOOR_OPENED,
                                    door -> door instanceof Door && "front door".equals(((Door) door).getName()),
                                    new RandomlyMoving()
                                )
                            );
                        case "waiting2":
                            return new Alien(
                                100,
                                new Observing<>(
                                    Door.DOOR_OPENED,
                                    door -> door instanceof Door && "back door".equals(((Door) door).getName()),
                                    new RandomlyMoving()
                                )
                            );
                        case "running":
                            return new Alien(100, new RandomlyMoving());
                        default:
                            return null;
                    }

                case "alien mother":
                    switch (type) {
                        case "waiting1":
                            return new MotherAlien(
                                new Observing<>(
                                    Door.DOOR_OPENED,
                                    door -> door instanceof Door && "front door".equals(((Door) door).getName()),
                                    new RandomlyMoving()
                                )
                            );
                        case "waiting2":
                            return new MotherAlien(
                                new Observing<>(
                                    Door.DOOR_OPENED,
                                    door -> door instanceof Door && "back door".equals(((Door) door).getName()),
                                    new RandomlyMoving()
                                )
                            );
                        case "running":
                            return new MotherAlien(new RandomlyMoving());
                        default:
                            return null;
                    }
                default:
                    return null;
            }
        }
    }
}
