package main;

import classes.*;

public class Main {

    public static void main(String[] args) {
        Map map = new Map(4);
        Person zhulik = new Person("Zhulik");
        map.setPosition(zhulik, 1, 0);
        map.move(zhulik, 0, 1);
        map.printPosition(zhulik);
    }
}
