package classes.abstracts;

import classes.Person;
import classes.enums.CarType;
import classes.exceptions.NoPersonException;
import classes.interfaces.Drivable;

import java.util.Vector;

public abstract class Transport implements Drivable {
    private int seats;
    private String model;
    private Person driver;
    private Vector<Person> passengers;

    public Transport(int seats){
        this.seats = seats;
        passengers = new Vector<Person>();
    }

    public Transport(int seats, Person driver){
        this.seats = seats;
        this.driver = driver;
        passengers = new Vector<Person>();
    }

    public Transport getIn(Person person){
        if (this.driver == null){
            this.driver = person;
            System.out.println(person.getName() + " got into " + this.getModel());
            return this;
        } else if (passengers.size() < seats - 1) {
            passengers.add(person);
            System.out.println(person.getName() + " got into " + this.getModel());
            return this;
        } else {
            System.out.println("No free place!");
            return this;
        }
    }

    public void leave(Person person) throws NoPersonException {
        if (this.driver.equals(person) && !passengers.isEmpty()){
            System.out.println("Driver cannot leave the car until everyone leaves!");
        } else if (this.driver.equals(person)){
            this.driver = null;
            System.out.println("Driver " + person.getName() + " left " + this.model);
        } else if (passengers.contains(person)){
            passengers.remove(person);
            System.out.println(person.getName() + " left " + this.model);
        } else {
            throw new NoPersonException("No such person in the car found!");
        }
    }

    public void leaveAll() {
        int passengersQuantity = passengers.size();
        for (int i = 0; i < passengersQuantity; i++) {
            try {
                leave(passengers.elementAt(0));
            } catch (NoPersonException e) {
                System.out.println("No person found!");
            }
        }
        try {
            this.leave(this.driver);
        } catch (NoPersonException e) {
            e.printStackTrace();
        }
    }

    public int getSeats() {
        return seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getDriver() {
        return driver;
    }

    public Vector<Person> getPassengers() {
        return passengers;
    }
}
