package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class SpawnPoint extends AbstractActor {
    private int spawnAliens;
    private final int spawnDistance;
    private boolean isSpawning;

    public SpawnPoint(int maxAliens) {
        this.spawnAliens = maxAliens;
        this.spawnDistance = 50;
        this.isSpawning = false;
        setAnimation(new Animation("sprites/spawn.png"));
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::spawnAlien)).scheduleFor(this);
    }

    private void spawnAlien() {
        if (spawnAliens <= 0 || isSpawning) {
            return;
        }

        Scene scene = getScene();
        if (scene == null) {
            return;
        }

        scene.getActors().stream()
            .filter(actor -> actor instanceof Ripley)
            .filter(this::isCloseTo)
            .findFirst()
            .ifPresent(ripley -> {
                spawner(scene);
                isSpawning = true;

                new ActionSequence<>(
                    new Wait<>(3),
                    new Invoke<>(() -> isSpawning = false)
                ).scheduleFor(this);
            });
    }

    private void spawner(Scene scene) {
        Alien alien = new Alien();
        scene.addActor(alien, getPosX(), getPosY());
        spawnAliens--;
    }

    private boolean isCloseTo(Actor actor) {
        int dx = getPosX() - actor.getPosX();
        int dy = getPosY() - actor.getPosY();
        return dx * dx + dy * dy <= spawnDistance * spawnDistance;
    }
}
