package classes;

import classes.abstracts.Thing;

public class Map {
    protected class Point{
        private boolean isEmpty_Person;
        private boolean isEmpty_Thing;
        private Person person;
        private Thing thing;

        public Point() {
            this.isEmpty_Thing = true;
            this.isEmpty_Person = true;
            this.person = null;
            this.thing = null;
        }

        protected void occupy(Person person){
            this.person = person;
            this.isEmpty_Person = false;
        }

        protected void put(Thing thing){
            this.thing = thing;
            isEmpty_Thing = false;
        }

        protected void leave(){
            this.person = null;
            isEmpty_Person = true;
        }

        protected void takeAway(){
            this.thing = null;
            isEmpty_Thing = true;
        }

        protected boolean isHere(Person person){
            if (this.person == person){
                return true;
            }
            else return false;
        }

        protected boolean isHere(Thing thing){
            if (this.thing == thing){
                return true;
            }
            else return false;
        }

        protected boolean isEmpty_Person() {
            return isEmpty_Person;
        }

        protected void setIsEmpty_Person(boolean empty_Person) {
            isEmpty_Person = empty_Person;
        }

        protected boolean isEmpty_Thing() {
            return isEmpty_Thing;
        }

        protected void setIsEmpty_Thing(boolean empty_Thing) {
            isEmpty_Thing = empty_Thing;
        }
    }

    private Point[][] points;
    private int size;

    public Map(int size) {
        this.size = size;
        points = new Point[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                points[i][j] = new Point();
            }
        }
    }

    /*
        All the points are located in First Quarter of Squared system of coordinates.
     */

    public int getSize() {
        return size;
    }

    public boolean setPosition(Person person, int x, int y){
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
            return false;
        }else {
            if (!pointIsEmptyPerson(x, y)) {
                System.out.println("This point is already taken, please, choose another");
                return false;
            } else {
                points[x][y].occupy(person);
                System.out.println(person.getName() + " is now located in point " + x + " " + y);
                pickUpThing(person, x, y); //
                return true;
            }
        }
    }

    public void setPosition(Thing thing, int x, int y){
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
        }else {
            if (!pointIsEmptyThing(x, y)) {
                System.out.println("This point is already taken, please, choose another");
            } else {
                points[x][y].put(thing);
                System.out.println(thing.getTitle() + " is placed in point " + x + " " + y);
            }
        }
    }

    public void deleteElement(Person person){
        int[] coordinates = getPosition(person);
        this.points[coordinates[0]][coordinates[1]].leave();
    }

    public void deleteElement(Thing thing){
        int[] coordinates = getPosition(thing);
        this.points[coordinates[0]][coordinates[1]].takeAway();
    }

    protected int[] getPosition(Person person){
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

    protected int[] getPosition(Thing thing){
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
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
        }else {
            if (!pointIsEmptyPerson(x, y)) {
                System.out.println("This point is already taken, please, choose another");
            } else {
                int[] position = getPosition(person);
                points[position[0]][position[1]].leave();
                points[x][y].occupy(person);
                System.out.println(person.getName() + " moved to point " + x + " " + y);

                pickUpThing(person, x, y);
            }
        }
    }

    public void pickUpThing(Person person, int x, int y){
        if (!pointIsEmptyThing(x, y) && !pointIsEmptyThing(x, y)){
            person.pickUpThing(this.points[x][y].thing);
            this.deleteElement(this.points[x][y].thing);
        }
    }

    public void move(Thing thing, int x, int y){
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
        }else {
            if (!pointIsEmptyThing(x, y)) {
                System.out.println("This point is already taken, please, choose another");
            } else {
                int[] position = getPosition(thing);
                points[position[0]][position[1]].takeAway();
                points[x][y].put(thing);
                System.out.println(thing.getTitle() + " was moved to point " + x + " " + y);
            }
        }
    }

    public void moveToPerson(Person person, Person victim){
        int xPerson, yPerson, xVictim, yVictim;
        xPerson = (getPosition(person))[0]; //getting coordinates of NPC
        yPerson = (getPosition(person))[1];
        xVictim = (getPosition(victim))[0]; //getting coordinates of victim
        yVictim = (getPosition(victim))[1];
        //checking if victim is near and moving NPC if necessary

        //checking if there's place to move by counting interations. If too many - break.
        int iterationQuantity = 0;
        boolean noPlaceFlag = false;

        if (!(Math.abs(xPerson - xVictim) < 2 && Math.abs(xPerson-yVictim)<2)){
            int newX = xPerson; //making new coordinates
            int newY = yPerson;
            while (!(Math.abs(newX - xVictim) < 2 && Math.abs(newY-yVictim)<2 && pointIsEmptyPerson(newX, newY))){ //moving to victim, if not near
                newX = (int) (Math.random() * (getSize()) + (xVictim-1));
                newY = (int) (Math.random() * (getSize()) + (yVictim-1));

                iterationQuantity++;
                if (iterationQuantity == this.size*this.size*this.size){
                    noPlaceFlag = true;
                    break;
                }
            }
            //moving if place was found
            if (!noPlaceFlag){
                move(person, newX, newY); //moving NPC
                printMap();
            }else{
                System.out.println("No place to move");
            }
        }
    }

    public void runAwayFrom(Person person, Person bully){
        int xPerson, yPerson, xBully, yBully;
        xPerson = (getPosition(person))[0]; //getting coordinates of NPC
        yPerson = (getPosition(person))[1];
        xBully = (getPosition(bully))[0]; //getting coordinates of bully
        yBully = (getPosition(bully))[1];

        int newX = xPerson; //making new coordinates
        int newY = yPerson;

        //checking if there's place to move by counting interations. If too many - break.
        int iterationQuantity = 0;
        boolean noPlaceFlag = false;

        while (!(Math.abs(newX - xBully) >= 2 && Math.abs(newY-yBully) >= 2 && pointIsEmptyPerson(newX, newY))){ //moving away
            newX = (int) (Math.random() * (getSize()));
            newY = (int) (Math.random() * (getSize()));

            iterationQuantity++;
            if (iterationQuantity == this.size*this.size*this.size){
                noPlaceFlag = true;
                break;
            }
        }
        //moving if place was found
        if (!noPlaceFlag){
            move(person, newX, newY); //moving NPC
            printMap();
        }else{
            System.out.println("No place to move");
        };
    }

    public void randomMove(Person person){
        int xPerson, yPerson;
        xPerson = (getPosition(person))[0]; //getting coordinates of NPC
        yPerson = (getPosition(person))[1];

        int newX = xPerson; //making new coordinates
        int newY = yPerson;

        //checking if there's place to move by counting interations. If too many - break.
        int iterationQuantity = 0;
        boolean noPlaceFlag = false;

        while (newX == xPerson && newY == yPerson){
            newX = (int) (Math.random() * getSize());
            newY = (int) (Math.random() * getSize());

            iterationQuantity++;
            if (iterationQuantity == this.size*this.size*this.size){
                noPlaceFlag = true;
                break;
            }
        }

        //moving if place was found
        if (!noPlaceFlag){
            move(person, newX, newY); //moving NPC
            printMap();
        }else{
            System.out.println("No place to move");
        }
        move(person, newX, newY); //moving NPC
        printMap();
    }

    public boolean pointIsEmptyPerson(int x, int y){
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
            return false;
        }else {
            if (points[x][y].isEmpty_Person()) {
                return true;
            } else return false;
        }
    }

    public boolean pointIsEmptyThing(int x, int y){
        if (!ifCoordinatesAreCorrectCheck(x, y)){
            System.out.println("Coordinates are not correct, please, input different");
            return false;
        }else {
            if (points[x][y].isEmpty_Thing()) {
                return true;
            } else return false;
        }
    }

    protected boolean ifCoordinatesAreCorrectCheck(int x, int y){
        //other boolean methods will also return false if coordinates are not correct
        if (x >= 0 && y >= 0 && x < this.size && y < this.size){
            return true;
        }
        else return false;
    }

    public void printMap(){
        System.out.println("Current state of map is:");
        String dotOut;
        StringBuilder sb = new StringBuilder();
        for (int i = this.size-1; i > -1; i--) {
            for (int j = 0; j < this.size; j++) {
                if (!points[j][i].isEmpty_Thing()) {
                    sb.append((char) points[j][i].thing.getTitle().charAt(0));
                }else {
                    sb.append("0");
                }
                if (!points[j][i].isEmpty_Person()){
                    sb.append((char) points[j][i].person.getName().charAt(0));
                }else {
                    sb.append("0");
                }
                dotOut = sb.toString();
                System.out.print(dotOut + " ");
                sb.delete(0, sb.length());
            }
            System.out.println();
        }
        System.out.println();
    }
}
