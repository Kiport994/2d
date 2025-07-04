//package sk.tuke.kpi.oop.game.scenarios;
//
//import sk.tuke.kpi.gamelib.*;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.oop.game.Locker;
//import sk.tuke.kpi.oop.game.Ventilator;
//import sk.tuke.kpi.oop.game.actions.Use;
//import sk.tuke.kpi.oop.game.characters.Ripley;
//import sk.tuke.kpi.oop.game.controllers.KeeperController;
//import sk.tuke.kpi.oop.game.controllers.MovableController;
//import sk.tuke.kpi.oop.game.items.AccessCard;
//import sk.tuke.kpi.oop.game.items.Ammo;
//import sk.tuke.kpi.oop.game.items.Energy;
//import sk.tuke.kpi.oop.game.openables.Door;
//import sk.tuke.kpi.oop.game.openables.LockedDoor;
//
//public class MissionImpossible implements SceneListener {
//    private Ripley ripley;
//    private Energy energy;
//
//    private Ventilator ventilator;
//
//    private Ammo ammo;
//
//    private Disposable stop_energy;
//
//    private Disposable reg_movable;
//    private Disposable reg_keeper;
//
//    @Override
//    public void sceneInitialized(Scene scene) {
//        ripley = scene.getFirstActorByType(Ripley.class);
//        if (ripley == null) {
//            return;
//        }
//
//        MovableController movableController = new MovableController(ripley, scene);
//        KeeperController keeperController = new KeeperController(ripley);
//
//        reg_movable = scene.getInput().registerListener(movableController);
//        reg_keeper = scene.getInput().registerListener(keeperController);
//
//        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, deadRipley -> {
//            reg_movable.dispose();
//            reg_keeper.dispose();
//        });
//
//        scene.follow(ripley);
//
//
//
//        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
//            stop_energy = new Loop<>(
//                new ActionSequence<>(
//                    new Invoke<>(() -> {
//                        if (ripley.getEnergy() > 0) {
//                            ripley.setEnergy(ripley.getEnergy() - 1);
//                        }
//                    }),
//                    new Wait<>(1.0f)
//                )
//            ).scheduleOn(scene);
//        });
//
//        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, ventilator -> {
//            stop_energy.dispose();
//        });
//
//        energy = scene.getFirstActorByType(Energy.class);
//        if (energy != null) {
//            new When<>(
//                () -> ripley.intersects(energy),
//                new Invoke<>(() -> new Use<>(energy).scheduleFor(ripley))
//            ).scheduleOn(scene);
//        }
//
//        ammo = scene.getFirstActorByType(Ammo.class);
//        if (ammo != null) {
//            new When<>(
//                () -> ripley.intersects(ammo),
//                new Invoke<>(() -> new Use<>(ammo).scheduleFor(ripley))
//            ).scheduleOn(scene);
//        }
//
//        scene.getGame().pushActorContainer(ripley.getBackpack());
//    }
//
//    @Override
//    public void sceneUpdating(Scene scene) {
//        if (ripley != null) {
//            ripley.showRipleyState();
//        }
//    }
//
//    public static class Factory implements ActorFactory {
//        @Override
//        public Actor create(String type, String name) {
//            if (name == null) {
//                return null;
//            }
//
//            Actor actor = null;
//
//            switch (name) {
//                case "energy":
//                    actor = new Energy();
//                    return actor;
//                case "ellen":
//                    actor = new Ripley();
//                    return actor;
//                case "ventilator":
//                    actor = new Ventilator();
//                    return actor;
//                case "door":
//                    actor = new LockedDoor();
//                    return actor;
//                case "access card":
//                    actor = new AccessCard();
//                    return actor;
//                case "locker":
//                    actor = new Locker();
//                    return actor;
//
//
//                default:
//                    return null;
//
//            }
//        }
//    }
//}
