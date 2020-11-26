package classes.things;

import classes.Person;
import classes.abstracts.Thing;

public class Food extends Thing {

    private int value;

    public Food(String title, int value) {
        super(title);
        this.value = value;
    }

    public void restoreHealth(Person person){
        person.changeHP(this.value);
    }
}
