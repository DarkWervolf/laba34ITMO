package classes.interfaces;

import classes.exceptions.NoPersonException;

public interface Drivable {
    public void go(String destination) throws NoPersonException;
}
