package classes.things;

import classes.Emotion;
import classes.Person;
import classes.abstracts.Thing;
import classes.enums.EmotionType;
import classes.enums.ThingType;

public class Container extends Thing {
    private int size;
    private boolean isEmptyPerson;
    private boolean isEmptyThing;
    private Person prisoner;
    private Thing treasure;

    public Container(String title, int size) {
        super(title, ThingType.CONTAINER);
        this.size = size;
        this.isEmptyPerson = true;
        this.isEmptyThing = true;
        this.prisoner = null;
        this.treasure = null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void use(Person victim) {
        if (this.isEmptyPerson){
            put(victim);
        }else{
            takeOut(victim);
        }
    }

    public void put(Person prisoner) {
        this.prisoner = prisoner;
        System.out.println(prisoner.getName() + " has successfully been prisoned");

        int value = (int) (Math.random() * 10);
        int type = (int) (Math.random() * 2);

        switch (type)
        {
            case 2: this.prisoner.setEmotion(new Emotion(value, EmotionType.NEUTRAL)); break;
            case 1: this.prisoner.setEmotion(new Emotion(value, EmotionType.SAD)); break;
            default: this.prisoner.setEmotion(new Emotion(value, EmotionType.ANGRY)); break;
        }

        this.isEmptyPerson = false;
    }

    public void put(Thing treasure) {
        this.treasure = treasure;
        this.isEmptyThing = false;
        System.out.println(treasure.getTitle() + " has been put into " + this.getTitle());
    }

    public void takeOut(Thing treasure){
        System.out.println(treasure.getTitle() + " has been taken out from " + this.getTitle());
        this.treasure = null;
        this.isEmptyThing = true;
    }

    public void takeOut(Person prisoner){
        System.out.println(prisoner.getName() + " has been given freedom");

        int value = (int) (Math.random() * 10);
        int type = (int) (Math.random() * 2);

        switch (type)
        {
            case 2: prisoner.setEmotion(new Emotion(value, EmotionType.NEUTRAL)); break;
            case 1: prisoner.setEmotion(new Emotion(value, EmotionType.ANGRY)); break;
            default: prisoner.setEmotion(new Emotion(value, EmotionType.HAPPY)); break;
        }

        this.prisoner = null;
        this.isEmptyPerson = true;
    }

    public boolean contains(Person prisoner){
        return this.prisoner == prisoner;
    }

    public boolean contains(Thing treasure){
        return this.treasure == treasure;
    }

    public boolean isEmptyPerson() {
        return isEmptyPerson;
    }

    public boolean isEmptyThing() {
        return isEmptyThing;
    }

    public Person getPrisoner() {
        return prisoner;
    }

    public Thing getTreasure() {
        return treasure;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 3;
        result = prime * result + this.size;
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
            Container another = (Container) o;
            if (another.getSize() == this.getSize()){
                return another.getPrisoner() == this.getPrisoner() && another.getTreasure() == this.getTreasure();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTitle()).append(' ').append(this.size);
        return sb.toString();
    }
}
