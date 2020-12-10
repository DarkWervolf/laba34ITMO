package classes.things;

import classes.Emotion;
import classes.Person;
import classes.abstracts.Thing;
import classes.enums.EmotionType;
import classes.enums.ThingType;

public class Container extends Thing {
    private int size;
    private boolean isEmpty;
    private Person prisoner;
    private Thing treasure;

    public Container(String title, int size) {
        super(title, ThingType.CONTAINER);
        this.size = size;
        this.isEmpty = true;
        this.prisoner = null;
        this.treasure = null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void use(Person victim) {
        if (this.isEmpty){
            put(victim);
        }else{
            takeOut(victim);
        }
    }

    public void put(Person prisoner) {
        this.prisoner = prisoner;
        this.isEmpty = false;
        System.out.println(prisoner.getName() + " has successfully been prisoned");

        int value = (int) (Math.random() * 10);
        int type = (int) (Math.random() * 2);

        switch (type)
        {
            case 2: this.prisoner.setEmotion(new Emotion(value, EmotionType.NEUTRAL)); break;
            case 1: this.prisoner.setEmotion(new Emotion(value, EmotionType.SAD)); break;
            default: this.prisoner.setEmotion(new Emotion(value, EmotionType.ANGRY)); break;
        }
    }

    public void put(Thing treasure) {
        this.isEmpty = false;
        this.treasure = treasure;
        System.out.println(treasure.getTitle() + " has been put into " + this.getTitle());
    }

    public void takeOut(Thing treasure){
        this.isEmpty = true;
        this.treasure = null;
        System.out.println(treasure.getTitle() + " has been taken out from " + this.getTitle());
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
        this.isEmpty = true;
    }

    public Person getPrisoner() {
        return prisoner;
    }

    public Thing getTreasure() {
        return treasure;
    }

    public boolean contains(Person prisoner){
        return this.prisoner == prisoner;
    }

    public boolean contains(Thing treasure){
        return this.treasure == treasure;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
