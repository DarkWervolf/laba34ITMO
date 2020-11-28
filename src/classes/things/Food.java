package classes.things;

import classes.Person;
import classes.abstracts.Thing;
import classes.enums.ThingType;

public class Food extends Thing {

    private int value;

    public Food(String title, int value) {
        super(title, ThingType.FOOD);
        this.value = value;
    }

    @Override
    public void use(Person person) {
        person.changeHP(this.value);
        StringBuilder sb = new StringBuilder();
        sb.append(person.getName()).append(" ate ").append(this.getTitle());
        System.out.println(sb.toString());
        person.throwAway(this);
    }
}
