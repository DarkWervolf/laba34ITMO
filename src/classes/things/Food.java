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

    public int getValue() {
        return value;
    }

    @Override
    public void use(Person person) {
        person.changeHP(this.value);
        StringBuilder sb = new StringBuilder();
        sb.append(person.getName()).append(" ate ").append(this.getTitle());
        System.out.println(sb.toString());
        person.throwAway(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 3;
        result = prime * result + this.value;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }

        if (o.getClass() != this.getClass()){
            return false;
        }

        if (this.hashCode() == o.hashCode()){
            Food another = (Food) o;
            return this.getTitle().equals(another.getTitle()) && this.getValue() == another.getValue();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTitle()).append(' ').append(this.getValue());
        return sb.toString();
    }
}
