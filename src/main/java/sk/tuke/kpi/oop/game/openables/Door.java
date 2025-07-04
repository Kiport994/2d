package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    private boolean opened;

    private Animation closeDoor;
    private Animation openDoor;

    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    private Orientation orientation;

    public Door(String name, Orientation orientation) {
        super(name);
        this.orientation = orientation;
        orientationDoor();
        setAnimation(closeDoor);
        this.opened = false;
    }

    public Door(Orientation orientation) {
        this("Door", orientation);
    }

    private void orientationDoor() {
        switch (orientation) {
            case HORIZONTAL:
                closeDoor = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE_REVERSED);
                openDoor = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
                break;
            case VERTICAL:
            default:
                closeDoor = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED);
                openDoor = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
                break;
        }
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);

        setTiles(MapTile.Type.WALL);
    }

    private void setTiles(MapTile.Type type) {
        int tileX = this.getPosX() / 16;
        int tileY = this.getPosY() / 16;

        if (this.orientation == Orientation.VERTICAL) {
            this.getScene().getMap().getTile(tileX, tileY).setType(type);
            this.getScene().getMap().getTile(tileX, tileY + 1).setType(type);
        } else if (this.orientation == Orientation.HORIZONTAL) {
            this.getScene().getMap().getTile(tileX, tileY).setType(type);
            this.getScene().getMap().getTile(tileX + 1, tileY).setType(type);
        }
    }

    @Override
    public void open() {
        setAnimation(openDoor);
        this.opened = true;

        getScene().getMessageBus().publish(DOOR_OPENED, this);

        setTiles(MapTile.Type.CLEAR);

    }

    @Override
    public void close() {
        setAnimation(closeDoor);
        this.opened = false;

        getScene().getMessageBus().publish(DOOR_CLOSED, this);

        setTiles(MapTile.Type.WALL);

    }

    @Override
    public void useWith(Actor actor) {
        if(isOpen()) {
            close();
        } else {
            open();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
