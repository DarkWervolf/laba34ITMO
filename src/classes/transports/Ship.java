package classes.transports;

import classes.Person;
import classes.abstracts.Transport;
import classes.exceptions.NoPersonException;
import classes.interfaces.Drivable;

public class Ship extends Transport implements Drivable {

    boolean isBerthed;

    public Ship(int seats) {
        super(seats);
        this.setModel("Ship");
        this.isBerthed = true;
    }

    public Ship(int seats, Person driver) {
        super(seats, driver);
        this.setModel("Ship");
        this.isBerthed = true;
    }

    public void berth(){
        this.isBerthed = true;
        System.out.println(this.getModel() + " was berthed!");
    }

    public void unberth(){
        this.isBerthed = false;
        System.out.println(this.getModel() + " is ready to go!");
    }

    @Override
    public void go(String destination) throws NoPersonException {
        if (this.getDriver() != null) {
            if (this.isBerthed) {
                System.out.println("Berthing off...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                unberth();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int randomLong = (int) (Math.random()*10 + 1);

                for (int i = 0; i < randomLong; i++) {
                    System.out.println("Sounds of sea...");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Finally we arrived on " + destination);
            }
        } else {
            throw new NoPersonException("Ship with no capitan!");
        }
    }
}
