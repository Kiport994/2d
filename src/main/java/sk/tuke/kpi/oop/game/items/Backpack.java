package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private String name;
    private int capacity;
    private List<Collectible> items;

    public Backpack(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        items = new ArrayList<Collectible>();
    }

    @Override
    public int getCapacity(){
        return capacity;
    }

    @Override
    public List<Collectible> getContent() {
        return List.copyOf(items);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public void add(Collectible item) throws IllegalStateException {
        if(getSize() == capacity ){
            throw new IllegalStateException(name + " is full");
        }
        items.add(item);
    }

    @Override
    public void remove(Collectible item) {
        items.remove(item);
    }

    @Override
    public Iterator<Collectible> iterator() {
        return items.iterator();
    }

    @Override
    public Collectible peek() {
        return items.isEmpty() ? null : items.get(items.size() - 1);
    }

    @Override
    public void shift() {
        if(items.size() > 1){
            Collections.rotate(items, -(items.size() - 1));
        }
    }


}
