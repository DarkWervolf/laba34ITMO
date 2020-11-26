package classes.abstracts;

public abstract class Thing {
    private String title;
    private ThingType type;

    public Thing(String title, ThingType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
