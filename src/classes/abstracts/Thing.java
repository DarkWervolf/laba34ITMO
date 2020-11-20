package classes.abstracts;

public abstract class Thing {
    private String title;
    private boolean isDestroyed;

    public Thing(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void destroy(){}

    public void repair(){}
}
