package main;

import classes.*;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        //model.runRandom();
        model.runWithParameters(4, 0, 16);
    }
}
