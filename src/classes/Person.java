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
    private int HP;
    private int maxHP;
    private Emotion currentEmotion;

    public Person(String name, int HP) {
        this.name = name;
        this.HP = HP;
        this.maxHP = 100;
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

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void changeHP(int change){
        StringBuilder sb = new StringBuilder();
        char sign; //sign of change
        if (change < 0){
            sign = '-';
        }else if (change > 0){
            sign = '+';
        } else sign = ' ';

        //making the change
        if (change+this.HP > maxHP){
            change = maxHP-this.HP;
            this.HP = this.maxHP;
        } else if (Math.abs(change) >= this.HP && (int) Math.signum(change) < 0){
            this.HP = 0;
            System.out.println(this.getName() + " died.");
        } else {
            this.HP += change;
        }

        //printing the change of HP
        sb.append(this.getName()).append(": ").append(sign).append(Math.abs(change)).append(" HP");
        System.out.println(sb.toString());

        //printing current HP
        sb.delete(0, sb.length());
        sb.append("Current HP of ").append(this.getName()).append(" is ").append(this.HP);
        System.out.println(sb.toString());
    }

    public void setEmotion(Emotion emotion) {
        this.currentEmotion = emotion;
        System.out.println(this.getName() + ": " + this.currentEmotion.express());
    }


    public void say(String text){
        System.out.println(this.getName() + ": " + text);
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
                victim.changeHP(-victim.getHP());
                break;
            case BEATING:
            case KICKING:
                System.out.println(this.getName() + action.perform(victim));
                victim.changeHP(-action.getValue());
                if (action.getValue() == 0){
                    System.out.println(this.getName() + " missed.");
                    victim.performAction(new ActionStatic(0, ActionTypeStatic.LAUTHING));
                    victim.say("LMAO, you're such a well-aimed fighter!");
                }
                if (victim.getHP() > 0 ) {
                    if (action.getValue() > 60) {
                        victim.setEmotion(new Emotion(action.getValue(), EmotionType.SAD));
                    } else {
                        victim.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                    }
                }
                break;
            case KISSING:
            case HUGGING:
                System.out.println(this.getName() + action.perform(victim));
                victim.changeHP(action.getValue());
                if (action.getValue() > 60){
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
        System.out.println(this.getName() + ": I was born with " + this.getHP() + " HP!");
    }

    @Override
    public void goNuts() {
        System.out.println(this.getName() + ": Братцы, братцы...");
    }
}
