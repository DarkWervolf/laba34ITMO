package main;

import classes.*;
import classes.abstracts.*;

public class Main {

    public static void main(String[] args) {
        Map map = new Map(4);

        Person zhulik = new Person("Zhulik");
        map.setPosition(zhulik, 1, 0);
        map.move(zhulik, 0, 1);
        map.printPosition(zhulik);

        Thing meshok = new Container("Meshok", 15, true);
        map.setPosition(meshok, 0, 1);
        map.move(meshok, 3, 2);
        map.printPosition(meshok);

        map.printMap();

        map.move(meshok, 0, 1);
        map.printMap();
    }
}
