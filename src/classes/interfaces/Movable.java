package classes.interfaces;

import classes.Map;
import classes.Person;

public interface Movable {
    void move(Map map, int x, int y);

    void runAwayFrom(Map map, Person bully);
}
