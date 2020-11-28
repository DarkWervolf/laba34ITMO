package classes.abstracts;

import classes.Person;
import classes.enums.ThingType;

public abstract class Thing {
    private String title;
    private ThingType type;

    public Thing(String title, ThingType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public ThingType getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void use(Person person){}
}
