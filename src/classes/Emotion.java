package classes;

import classes.enums.EmotionType;

public class Emotion {
    private String title;
    private int value;
    private EmotionType type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EmotionType getType() {
        return type;
    }

    public void setType(EmotionType type) {
        this.type = type;
    }

    public void printReport(){

    }
}
