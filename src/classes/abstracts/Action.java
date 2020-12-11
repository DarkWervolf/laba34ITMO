package classes.abstracts;

import classes.Emotion;

public abstract class Action {
    private String title;
    private int value;
    private Emotion emotion;

    public Action(int value) {
        this.value = value;
    }

    public Action(String title, int value) {
        this.title = title;
        this.value = value;
    }

    public Action(String title, int value, Emotion emotion){
        this.title = title;
        this.value = value;
        this.emotion = emotion;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }
}
