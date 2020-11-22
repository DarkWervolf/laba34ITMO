package classes;

import classes.interfaces.Alive;
import classes.interfaces.Movable;

import java.util.Vector;

public class Person implements Movable, Alive {

    private String name;
    private Vector<Action> actions;
    private Vector<Emotion> emotions;

    public Person(String name) {
        this.name = name;
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

    public void addEmotion(Emotion emotion) {
        this.emotions.add(emotion);
    }

    public void say(String text){
        System.out.println(text);
    }

    public void performAction(Action a){
        this.addEmotion(a.getEmotion());
        a.perform();
    }

    public void deleteEmotion(Emotion emotion){
        this.emotions.remove(emotion);
    }

    public boolean isPrisoner(Container container){
        if (container.contains(this)){
            return true;
        }
        else return false;
    }

    @Override
    public void move(Map map, int x, int y) {
        //will be made soon
    }

    @Override
    public void shoutImAlive() {
        System.out.println("Hey, I'm alive, look at me!");
    }

    @Override
    public void goNuts() {
        System.out.println("jafjgfggjsfsgkjfgfhegfyrgyfgfbdsndfbvkhbs");
    }
}
