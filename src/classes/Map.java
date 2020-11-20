package classes;

import classes.abstracts.Thing;

public class Map {
    public class Point{
        private int x;
        private int y;
        private boolean isEmpty;
        private Person person;
        private Thing thing;

        public void occupy(Person person){
            this.person = person;
        }

        public void occupy(Thing thing){
            this.thing = thing;
        }

        public void leave(){
            this.person = null;
        }

        public boolean isHere(Person person){
            if (this.person == person){
                return true;
            }
            else return false;
        }

        public boolean isHere(Thing thing){
            if (this.thing == thing){
                return true;
            }
            else return false;
        }
    }

    private Point[][] points;
    private int size;

    public void setPosition(Person person, int x, int y){
        points[x][y].occupy(person);
    }

    public void setPosition(Thing thing, int x, int y){
        points[x][y].occupy(thing);
    }

    public String getPosition(Person person){
        String out = null;
        for (int x = 0; x < points.length; x++){
            for (int y = 0; y < points.length; y++){
                if (points[x][y].isHere(person)){
                    out = person.getName() + "is in point " + x + " " + y;
                }
            }
        }
        if(out != null){
            return out;
        }
        else{
            out = person.getName() + " wasn't found";
            return out;
        }
    }

    public String getPosition(Thing t){

    }

    public void move(Person p, int xIn, int yIn){

    }

    public void move(Thing t, int xIn, int yIn){

    }

    public boolean pointIsEmpty(int xIn, int yIn){

    }
}
