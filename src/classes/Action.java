package classes;

import classes.enums.ActionType;

public class Action {
    private String title;
    private ActionType type;
    private Emotion emotion;

    public Action(String title, ActionType type) {
        this.title = title;
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public void perform(){
        System.out.println(this.getType().toString() + "has been performed");
    }
}
