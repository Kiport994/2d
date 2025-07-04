//package sk.tuke.kpi.oop.game.scenarios;
//
//import sk.tuke.kpi.gamelib.GameApplication;
//import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.SceneListener;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.gamelib.graphics.Overlay;
//import sk.tuke.kpi.oop.game.actions.Use;
//import sk.tuke.kpi.oop.game.characters.Ripley;
//import sk.tuke.kpi.oop.game.controllers.KeeperController;
//import sk.tuke.kpi.oop.game.controllers.MovableController;
//import sk.tuke.kpi.oop.game.items.*;
//
//public class FirstSteps implements SceneListener {
//
//    private Ripley ripley;
//    private Energy energy;
//    private Ammo ammo;
//
//    @Override
//    public void sceneInitialized(Scene scene) {
//        ripley = new Ripley();
//        scene.addActor(ripley, 0, 0);
//
//        MovableController controller = new MovableController(ripley, scene);
//        KeeperController keeper = new KeeperController(ripley);
//
//        scene.getInput().registerListener(controller);
//        scene.getInput().registerListener(keeper);
//
//        energy = new Energy();
//        scene.addActor(energy, 100, 100);
//
//        ammo = new Ammo();
//        scene.addActor(ammo, 50, 50);
//
//        new When<>(
//            () -> ripley.intersects(energy),
//            new Invoke<>(
//                () -> {
//                    new Use<>(energy).scheduleFor(ripley);
//                }
//            )
//        ).scheduleOn(scene);
//
//        new When<>(
//            () -> ripley.intersects(ammo),
//            new Invoke<>(
//                () -> {
//                    new Use<>(ammo).scheduleFor(ripley);
//                }
//            )
//        ).scheduleOn(scene);
//
//        Wrench wrench = new Wrench();
//        Hammer hammer = new Hammer();
//        FireExtinguisher fireExtinguisher = new FireExtinguisher();
//
//        ripley.getBackpack().add(hammer);
//        ripley.getBackpack().add(wrench);
//        ripley.getBackpack().add(fireExtinguisher);
//
//        scene.getGame().pushActorContainer(ripley.getBackpack());
//
//    }
//
//    @Override
//    public void sceneUpdating(Scene scene) {
//        ripley.showRipleyState();
//    }
//}
