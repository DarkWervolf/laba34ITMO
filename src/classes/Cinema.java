package classes;

import java.util.Vector;

public class Cinema {
    private int seats;
    private Vector<Person> audience;
    private String movie;
    private int duration; //in hours

    public Cinema(int seats, String movie, int duration) {
        this.seats = seats;
        this.movie = movie;
        this.duration = duration;
    }

    public void enter(Vector<Person> audience){
        this.audience = (Vector<Person>) audience.clone();
        System.out.println("Everyone is ready to watch " + this.movie);
    }

    public Vector<Person> exit(){
        Vector<Person> e = this.audience;
        this.audience.clear();
        return e;
    }

    public void watch(){
        for (int i = 0; i < duration; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("One hour later");
        }
        System.out.println();

        int randomReaction = (int) (Math.random()*2);

        switch (randomReaction){
            case 0:
                System.out.println("Sounds of applause");
                break;
            case 1:
                System.out.println("Audience: It's the worst movie we've ever seen!");
                break;
        }
        System.out.println();
    }
}
