package classes.transports;

import classes.Person;
import classes.abstracts.Transport;
import classes.exceptions.NoPersonException;
import classes.interfaces.Drivable;

public class Train extends Transport implements Drivable {
    public Train(int seats) {
        super(seats);
        this.setModel("Train");
    }

    public Train(int seats, Person driver) {
        super(seats, driver);
        this.setModel("Train");
    }

    public void too(){
        System.out.println("Too Too!");
    }

    @Override
    public void go(String destination) throws NoPersonException {
        if (this.getDriver() != null) {
            System.out.println(this.getModel() + " is starting...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            too();

            int randomLong = (int) (Math.random()*8 + 1);
            int randomPassenger;

            //performing random-long journey
            for (int i = 0; i < randomLong; i++) {
                System.out.println("Сlickety-clack...Сlickety-clack...");

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
            throw new NoPersonException("Train can't go without the driver!");
        }
    }
}
