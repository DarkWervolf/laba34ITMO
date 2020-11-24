package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.ActionTypeStatic;
import classes.enums.EmotionType;
import classes.interfaces.Alive;
import classes.interfaces.Movable;
import java.util.Vector;

public class Person implements Movable, Alive {

    private String name;
    private Emotion currentEmotion;

    public Person(String name) {
        this.name = name;
        currentEmotion = new Emotion();
        this.shoutIwasBorn();
        this.randomEmotion();
    }

    protected void randomEmotion(){
        int value = (int) (Math.random() * 10);
        int type = (int) (Math.random() * 3);

        switch (type)
        {
            case 3: this.setEmotion(new Emotion(value, EmotionType.SAD)); break;
            case 2: this.setEmotion(new Emotion(value, EmotionType.NEUTRAL)); break;
            case 1: this.setEmotion(new Emotion(value, EmotionType.ANGRY)); break;
            default: this.setEmotion(new Emotion(value, EmotionType.HAPPY)); break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmotion(Emotion emotion) {
        this.currentEmotion = emotion;
        System.out.println(this.getName() + ": " + this.currentEmotion.express());
    }

    public void say(String text){
        System.out.println(text);
    }

    public void performAction(ActionStatic action){
        System.out.println(this.getName() + action.perform());
        if (action.getEmotion() != null){
            this.setEmotion(action.getEmotion());
        }
        else{
            this.randomEmotion();
        }
    }

    public void performAction(ActionPerson action, Person victim){
        switch (action.getType())
        {
            case EATING:
                System.out.println(this.getName() + action.perform(victim));
                victim.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                victim.goNuts();
                break;
            case BEATING:
            case KICKING:
                System.out.println(this.getName() + action.perform(victim));
                if (action.getValue() > 5){
                    victim.setEmotion(new Emotion(action.getValue(), EmotionType.SAD));
                } else{
                    victim.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                }
                break;
            case HUGGING:
                System.out.println(this.getName() + action.perform(victim));
                if (action.getValue() > 5){
                    victim.setEmotion(new Emotion(action.getValue(), EmotionType.HAPPY));
                } else{
                    victim.setEmotion(new Emotion(action.getValue(), EmotionType.NEUTRAL));
                }
                break;
            default:
                System.out.println(this.getName() + action.perform(victim));
                victim.randomEmotion();
                System.out.println(victim.getName() + ": what the hell just happend?");
        }
    }

    public void performAction(Action action, Thing thing){
        //in development
    }

    protected boolean isPrisoner(Container container){
        if (container.contains(this)){
            return true;
        }
        else return false;
    }

    @Override
    public void move(Map map, int x, int y) {
        map.move(this, x, y);
    }

    @Override
    public void shoutIwasBorn() {
        System.out.println(this.getName() + ": I was born!");
    }

    @Override
    public void goNuts() {
        System.out.println("Братцы, братцы...");
    }
}
