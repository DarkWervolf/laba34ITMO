package classes.enums;

public enum EmotionType {
    NEUTRAL("I don't care", "Well, okay", "Whatever"),
    SAD("Just kill me please!", "I wanna cry...", "Eh..."),
    HAPPY("I'm so happy!", "Great!", "Nice"),
    ANGRY("I'm gonna kill someone now!", "What the hell?", "Damn");

    private final String intense;
    private final String medium;
    private final String low;

    EmotionType(String intense, String medium, String low) {
        this.intense = intense;
        this.medium = medium;
        this.low = low;
    }

    public String getIntense() {
        return intense;
    }

    public String getMedium() {
        return medium;
    }

    public String getLow() {
        return low;
    }
}
