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

    public Map(int size) {
        this.size = size;
        points = new Point[size][size];
    }

    /*
        All the points are located in First Quarter of Squared system of coordinates.
     */

    public void setPosition(Person person, int x, int y){
        if (!points[x][y].isEmpty){
            System.out.println("This point is already taken, please, choose another");
        }else {
            points[x][y].occupy(person);
            System.out.println(person.getName() + " is now located in point " + x + " " + y);
        }
    }

    public void setPosition(Thing thing, int x, int y){
        if (!points[x][y].isEmpty){
            System.out.println("This point is already taken, please, choose another");
        }else {
            points[x][y].occupy(thing);
            System.out.println(thing.getTitle() + " is placed in point " + x + " " + y);
        }
    }

    public int[] getPosition(Person person){
        int[] position = new int[2]; //x, y coordinates of a person
        boolean flag_posFound = false; //checking, if person is found
        for (int x = 0; x < points.length; x++){
            for (int y = 0; y < points.length; y++){
                if (points[x][y].isHere(person)){
                    position[0] = x;
                    position[1] = y;
                    flag_posFound = true;
                }
            }
        }
        if (flag_posFound){
            return position;
        }else{
            return null;
        }
    }

    public int[] getPosition(Thing thing){
        int[] position = new int[2]; //x, y coordinates of a person
        boolean flag_posFound = false; //checking, if person is found
        for (int x = 0; x < points.length; x++){
            for (int y = 0; y < points.length; y++){
                if (points[x][y].isHere(thing)){
                    position[0] = x;
                    position[1] = y;
                    flag_posFound = true;
                }
            }
        }
        if (flag_posFound){
            return position;
        }else{
            return null;
        }
    }

    public void printPosition(Person person){
        int[] position = getPosition(person);
        if (position != null){
            System.out.println(person.getName() + " is in point " + position[0] + " " + position[1]);
        }else {
            System.out.println(person.getName() + " was not found");
        }
    }

    public void printPosition(Thing thing){
        int[] position = getPosition(thing);
        if (position != null){
            System.out.println(thing.getTitle() + " is in point " + position[0] + " " + position[1]);
        }else {
            System.out.println(thing.getTitle() + " was not found");
        }
    }

    public void move(Person person, int x, int y){
        if (!points[x][y].isEmpty){
            System.out.println("This point is already taken, please, choose another");
        }else {
            int[] position = getPosition(person);
            points[position[0]][position[1]].leave();
            points[x][y].occupy(person);
            System.out.println(person.getName() + " moved to point " + x + " " + y);
        }
    }

    public void move(Thing thing, int x, int y){
        if (!points[x][y].isEmpty){
            System.out.println("This point is already taken, please, choose another");
        }else {
            int[] position = getPosition(thing);
            points[position[0]][position[1]].leave();
            points[x][y].occupy(thing);
            System.out.println(thing.getTitle() + " was moved to point " + x + " " + y);
        }
    }

    public boolean pointIsEmpty(int x, int y){
        if (points[x][y].isEmpty){
            return true;
        }
        else return false;
    }
}
