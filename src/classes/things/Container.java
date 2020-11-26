package classes.things;

import classes.Emotion;
import classes.Person;
import classes.abstracts.Thing;
import classes.enums.EmotionType;

public class Container extends Thing {
    private int size;
    private boolean isEmpty;
    private Person prisoner;
    private Thing treasure;

    public Container(String title, int size, ) {
        super(title);
        this.size = size;
        this.isEmpty = true;
        this.prisoner = null;
        this.treasure = null;
    }

    public int getSize() {
        return size;
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
    }

    public void put(Thing treasure) {
        this.treasure = treasure;
        System.out.println(treasure.getTitle() + " has been put into " + this.getTitle());
    }

    public void takeOut(Thing treasure){
        this.treasure = null;
        System.out.println(treasure.getTitle() + " has been taken out from " + this.getTitle());
    }

    public void takeOut(Person prisoner){
        this.prisoner = null;
        System.out.println(prisoner.getName() + " has been given freedom");

        int value = (int) (Math.random() * 10);
        int type = (int) (Math.random() * 2);

        switch (type)
        {
            case 2: prisoner.setEmotion(new Emotion(value, EmotionType.NEUTRAL)); break;
            case 1: prisoner.setEmotion(new Emotion(value, EmotionType.ANGRY)); break;
            default: prisoner.setEmotion(new Emotion(value, EmotionType.HAPPY)); break;
        }
    }

    public boolean contains(Person prisoner){
        if (this.prisoner == prisoner){
            return true;
        }
        else return false;
    }

    public boolean contains(Thing treasure){
        if (this.treasure == treasure){
            return true;
        }
        else return false;
    }
}
