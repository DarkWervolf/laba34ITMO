package classes;

import classes.actions.ActionPerson;
import classes.actions.ActionStatic;
import classes.abstracts.Thing;
import classes.enums.EmotionType;
import classes.interfaces.Alive;
import classes.interfaces.Movable;

import java.util.Vector;

public class Person implements Movable, Alive {

    private String name;
    private int HP;
    private int maxHP;
    private int ID;
    private boolean isPrisoner;
    private Emotion currentEmotion;
    private Vector<Thing> inventory;

    public Person(String name, int HP, int ID) {
        this.name = name;
        this.HP = HP;
        this.ID = ID;
        this.maxHP = 100;
        this.isPrisoner = false;
        currentEmotion = new Emotion();
        this.shoutIwasBorn();
        this.randomEmotion();
        this.inventory = new Vector<>();
    }

    public void randomEmotion(){
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
        boolean diedFlag = false;
        if (change+this.HP > maxHP){
            change = maxHP-this.HP;
            this.HP = this.maxHP;
        } else if (Math.abs(change) >= this.HP && (int) Math.signum(change) < 0){
            this.HP = 0;
            System.out.println(this.getName() + " died.");
            diedFlag = true;
        } else {
            this.HP += change;
        }

        if (!diedFlag) {
            //printing the change of HP
            sb.append(this.getName()).append(": ").append(sign).append(Math.abs(change)).append(" HP");
            System.out.println(sb.toString());

            //printing current HP
            sb.delete(0, sb.length());
            sb.append("Current HP of ").append(this.getName()).append(" is ").append(this.HP);
            System.out.println(sb.toString());
        }
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
        action.perform(this, victim);
    }

    public void pickUpThing(Thing thing){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(" picked up ").append(thing.getTitle());
        System.out.println(sb.toString());
        this.inventory.add(thing);
    }

    public void throwAway(Thing thing){
        this.inventory.remove(thing);
    }

    public int inventorySize(){
        return inventory.size();
    }

    public Thing getThing(int index){
        return this.inventory.elementAt(index);
    }

    public void useThing(Thing thing){
        thing.use(this);
    }

    public void useThingOn(Thing thing, Person victim){
        thing.use(victim);
    }

    public boolean isPrisoner(){
        return isPrisoner;
    }

    public void makePrisoner(){
        this.isPrisoner = true;
    }

    public void makeFree(){
        this.isPrisoner = false;
    }

    @Override
    public void runAwayFrom(Map map, Person bully) {
        map.runAwayFrom(this, bully);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ID).append(" ").append(this.getName()).append(" ").append(this.getHP());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.ID;
        result = prime * result + this.maxHP;
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
            Person another = (Person) o;
            return this.name.equals(another.getName()) && this.ID == another.ID;
        } else {
            return false;
        }
    }
}
