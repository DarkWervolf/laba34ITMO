package classes.transports;

import classes.Person;
import classes.abstracts.Transport;
import classes.exceptions.NoPersonException;
import classes.interfaces.Drivable;

public class Rocket extends Transport implements Drivable {

    private int weight;
    private int partsQuantity;
    private int partWeight;

    public Rocket(int seats, int partsQuantity, int partWeight) {
        super(seats);
        this.setModel("Rocket");

        if (partsQuantity <= 0){
            this.partsQuantity = 3;
        } else {
            this.partsQuantity = partsQuantity;
        }
        if (partWeight <= 0){
            this.partWeight = 100; //in kilograms
        }
        this.weight = partsQuantity * partWeight;
    }

    public void looseWeight(){
        if (partsQuantity > 1) {
            this.partsQuantity--;
            this.weight -= partWeight;
            System.out.println(this.getModel() + " has lost module!");
        } else {
            System.out.println("It's the last module, capitan!");
        }
    }

    @Override
    public void go(String destination) throws NoPersonException {
        if (this.getDriver() != null) {

            System.out.println(this.getModel() + " is ready to go!");

            for (int i = 10; i > 0; i--) {
                System.out.println(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("GO!");
            System.out.println(this.getModel() + " has successfully started!");

            for (int i = 0; i <= partsQuantity; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                looseWeight();
            }

            //performing random-long journey
            int randomLong = (int) (Math.random() * 10 + 1);
            int randomPassenger;

            for (int i = 0; i < randomLong; i++) {
                System.out.println("Flying...");

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
            throw new NoPersonException("Rocket can't be launched without astronauts!");
        }
    }
}
