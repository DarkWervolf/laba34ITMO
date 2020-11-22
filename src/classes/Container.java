package classes;

import classes.abstracts.Thing;

public class Container extends Thing {
    private int size;
    private boolean isEmpty;
    private Person prisoner;
    private Thing treasure;

    public Container(String title, int size, boolean isEmpty) {
        super(title);
        this.size = size;
        this.isEmpty = isEmpty;
        this.prisoner = null;
        this.treasure = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void put(Person prisoner) {
        this.prisoner = prisoner;
    }

    public void put(Thing treasure) {
        this.treasure = treasure;
    }

    public void takeOut(Thing treasure){
        this.treasure = null;
    }

    public void takeOut(Person prisoner){
        this.prisoner = null;
    }

    public boolean contains(Person prisoner){
        if (this.prisoner == prisoner){
            return true;
        }
        else return false;
    }

    public boolean contains(Thing treasure){
        if (this.treasure == treasure){
            return true;
        }
        else return false;
    }
}
