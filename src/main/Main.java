package main;

import classes.*;

public class Main {

    public static void main(String[] args) {
        /*
        Map map = new Map(4);

        Person vintik = new Person("Vintik");
        Person shpuntik = new Person("Shpuntik");
        Person donut = new Person("Donut");

        map.setPosition(vintik, 1, 0);
        map.setPosition(shpuntik, 0, 1);
        map.setPosition(donut, 0, 0);

        donut.move(map, 3, 2);

        map.printMap();

        Container meshok = new Container("Meshok", 10);

        Action slightKick = new Action(3, ActionTypeStatic.KICKING);
        vintik.performAction(slightKick, donut);
        meshok.put(donut);

        meshok.takeOut(donut);
        */

        Model model = new Model();

        model.runRandom();
    }
}
