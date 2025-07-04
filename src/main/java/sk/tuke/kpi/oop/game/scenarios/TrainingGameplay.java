//package sk.tuke.kpi.oop.game.scenarios;
//import sk.tuke.kpi.gamelib.Scene;
//import sk.tuke.kpi.gamelib.framework.Scenario;
//import sk.tuke.kpi.oop.game.*;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.oop.game.items.Hammer;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//
//public class TrainingGameplay extends Scenario implements Repairable {
//    private Reactor reactor;
//    private Computer computer;
//    private Hammer hammer;
//    private Cooler cooler;
//    private Light light;
//    private DefectiveLight defectiveLight;
//
//    @Override
//    public void setupPlay(Scene scene) {
//        initialize(scene);
//        turnOn();
//        work_Cooler();
//        repair();
//        work_Light();
//        work_defectiveLight(scene);
//    }
//
//    private void initialize(Scene scene) {
//        reactor = new Reactor();
//        scene.addActor(reactor, 64, 64);
//
//        cooler = new Cooler(reactor);
//        scene.addActor(cooler, 64, 30);
//
////        hammer = new Hammer();
////        scene.addActor(hammer, 100, 40);
//
//        computer = new Computer();
//        scene.addActor(computer, 240, 350);
//
//        light = new Light();
//        scene.addActor(light, 80, 150);
//
//        defectiveLight = new DefectiveLight();
//    }
//
//    private void turnOn() {
//        reactor.turnOn();
//    }
//
//    @Override
//    public boolean repair() {
//        if (hammer != null) {
//            new When<>(
//                () -> reactor.getTemperature() >= 3000,
//                new Invoke<>(() -> {
//                    boolean repaired = reactor.repair();
//                    if (repaired) {
//                        hammer.deleteHammer();
//                    }
//                })
//            ).scheduleFor(reactor);
//            return true;
//        }
//        return false;
//    }
//
//    private void work_Cooler() {
//        new ActionSequence<>(
//            new Wait<>(5),
//            new Invoke<>(cooler::turnOn)
//        ).scheduleFor(cooler);
//    }
//
//    private void work_Light() {
//        reactor.addDevice(light);
//        new When<>(
//            () -> reactor.isOn(),
//            new Invoke<>(() -> {
//                light.setPowered(true);
//                light.toggle();
//            })
//        ).scheduleFor(light);
//        new When<>(
//            () -> !reactor.isOn(),
//            new Invoke<>(() -> {
//                light.setPowered(false);
//                light.toggle();
//            })
//        ).scheduleFor(light);
//    }
//
//    private void work_defectiveLight(Scene scene) {
//        new ActionSequence<>(
//            new Wait<>(10),
//            new Invoke<>(() -> {
//                scene.addActor(defectiveLight, 80, 150);
//                reactor.addDevice(defectiveLight);
//            })
//        ).scheduleFor(reactor);
//
//        new When<>(
//            () -> reactor.isOn(),
//            new Invoke<>(() -> {
//                if (defectiveLight != null) {
//                    defectiveLight.setPowered(true);
//                    defectiveLight.toggle();
//                }
//            })
//        ).scheduleFor(reactor);
//
//        new When<>(
//            () -> !reactor.isOn(),
//            new Invoke<>(() -> {
//                if (defectiveLight != null) {
//                    defectiveLight.setPowered(false);
//                    defectiveLight.toggle();
//                }
//            })
//        ).scheduleFor(reactor);
//    }
//
//
//}
