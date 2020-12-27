package main;

import classes.*;
import classes.interfaces.Startable;

public class Main {

    public static void main(String[] args) {
        //commented block below will cause fatal (RunTime) error because of 0 NPC input
        //Model model = new Model();
        //model.runWithParameters(4, 0, 16);

        //example of using anonymous classes
        Startable startModel = new Startable() {
            @Override
            public void start() {
                System.out.println("Here's the model!");

                Model model = new Model();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                model.runRandom();
            }
        };

        startModel.start();
    }
}
