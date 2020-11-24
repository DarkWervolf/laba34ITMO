package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.ActionType;
import classes.enums.ActionTypePerson;
import classes.enums.EmotionType;
import classes.interfaces.Alive;
import classes.interfaces.Movable;
import java.util.Vector;

public class Person implements Movable, Alive {

    private String name;
    private Vector<Action> actions;
    private Emotion currentEmotion;

    public Person(String name) {
        this.name = name;
        actions = new Vector<>();
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

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public Action getAction(int index) {
        return actions.elementAt(index);
    }

    public int getActionsSize(){
        return actions.size();
    }

    public void setEmotion(Emotion emotion) {
        this.currentEmotion = emotion;
        System.out.println(this.getName() + ": " + this.currentEmotion.express());
    }

    public void say(String text){
        System.out.println(text);
    }

    public void performAction(Action action){
        System.out.println(this.getName() + action.perform());
        if (action.getEmotion() != null){
            this.setEmotion(action.getEmotion());
        }
        else{
            this.randomEmotion();
        }
    }

    public void performAction(Action action, Person person){
        switch (action.getType())
        {
            case EATING:
                System.out.println(this.getName() + ActionTypePerson.EATING.outStringAction() + person.getName());
                person.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                person.goNuts();
                break;
            case KICKING:
                System.out.println(this.getName() + ActionTypePerson.KICKING.outStringAction() + person.getName());
                if (action.getValue() > 5){
                    person.setEmotion(new Emotion(action.getValue(), EmotionType.SAD));
                } else{
                    person.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                }
                break;
            case THROWING:
                System.out.println(this.getName() + ActionType.THROWING.outStringAction() + person.getName());
                person.setEmotion(new Emotion(action.getValue(), EmotionType.ANGRY));
                person.goNuts();
                break;
            default:
                System.out.println(this.getName() + action.getType().outStringAction());
                System.out.println(person.getName() + ": what the hell just happend?");
        }
    }

    public void performAction(Action action, Thing thing){

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
