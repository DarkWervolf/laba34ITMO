package classes;

import classes.enums.EmotionType;

public class Emotion {
    private String title;
    private int value;
    private EmotionType type;

    public Emotion(int value, EmotionType type) {
        this.value = value;
        this.type = type;
    }

    protected Emotion() {}

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

    public String express(){
        int intensity = this.getValue();
        if (intensity > 7) {
            return this.type.getIntense();
        }else if (intensity > 4){
            return this.type.getMedium();
        }
        else {
            return this.type.getLow();
        }
    }
}
