package sk.tuke.kpi.oop.game.openables;

public class LockedDoor extends Door {
    private boolean locked;
    public LockedDoor(String name, Orientation orientation) {
        super(name, orientation);
        this.locked = true;
    }

    public void lock() {
        if(!locked) {
            locked = true;
            close();
        }

    }

    public void unlock() {
        if(locked) {
            locked = false;
            open();
        }
    }

    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public void open() {
        if(!locked) {
            super.open();
        }
    }

    @Override
    public void close() {
        if(locked) {
            super.close();
        }
    }
}
