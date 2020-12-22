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

            //performing random-long journey
            int randomLong = (int) (Math.random()*6 + 1);
            int randomPassenger;

            for (int i = 0; i < randomLong; i++) {
                System.out.println("Driving...");

                //driver's and passenger's emotions
                getDriver().randomEmotion();
                if (getPassengers().size() > 0) {
                    randomPassenger = (int) (Math.random() * getPassengers().size());
                    getPassengers().elementAt(randomPassenger).randomEmotion();
                }

                System.out.println();//empty line to make the output look better

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
