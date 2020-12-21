package classes.transports;

import classes.Person;
import classes.abstracts.Transport;
import classes.enums.CarType;
import classes.exceptions.NoPersonException;
import classes.interfaces.Drivable;

import java.util.Vector;

public class Car extends Transport implements Drivable {

    CarType type;

    public Car(int seats, CarType type){
        super(seats);
        this.setModel("Car");
        this.type = type;
    }

    public Car(int seats, CarType type, Person driver){
        super(seats, driver);
        this.setModel("Car");
        this.type = type;
    }

    @Override
    public void go(String destination) throws NoPersonException {
        if (this.getDriver() != null) {
            System.out.println("Strating the car...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getModel() + " has started!");

            int randomLong = (int) (Math.random()*10 + 1);

            for (int i = 0; i < randomLong; i++) {
                System.out.println("Driving...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Finally we arrived on " + destination);
        } else {
            throw new NoPersonException("No driver!");
        }
    }

    public void beep(){
        System.out.println("Beep beep!");
    }

    public CarType getType() {
        return type;
    }
}
