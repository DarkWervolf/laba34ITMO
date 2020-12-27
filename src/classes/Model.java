package classes;

import classes.abstracts.Transport;
import classes.actions.ActionPerson;
import classes.actions.ActionStatic;
import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.*;
import classes.exceptions.InvalidParametrsRunTimeException;
import classes.exceptions.NoPersonException;
import classes.things.Container;
import classes.things.Food;
import classes.transports.Car;
import classes.transports.Rocket;
import classes.transports.Ship;
import classes.transports.Train;

import java.util.Vector;

public class Model {
    private Map map;
    private Vector<Person> NPCs;
    private Vector<Thing> things;

    public Model() {}

    /*
    Scheme (schort):
        1. Setting up the parameters: full random or given
        2. Creating model
        3. Action, including travelling to another map (always random) and going to cinema
        4. Duel or Happy End
     */
    /*
    How it works (in random):
        We create a map of random size within reason and placing there things and people.
    People are free to move around, so they move to one another and perform some actions (PersonAction).
    Also, without moving, they can do static action.
    Person action contains prisoning, so one person can put another into cage and keep there until he wants to free the prisoner.
    Then he lets him go.
    NPCs can go and watch movie.
        People can also travel: they get in some transport, drive, and while driving they express emotions - one is always driver's emotion,
    another is random passenger's emotion. When they arrive, the new map with new random things is being created, where they take positions.
    Then action continues.
        When there are only 2 people left, they will do one of two things (randomly): they'll go and live a long life, or they will fight.
     */

    protected void createModel(int sizeOfMap, int NPCquantity, int thingsQuantity){
        map = new Map(sizeOfMap);

        //maiking random things
        int thingType;
        things = new Vector<>();
        for (int i = 0; i < thingsQuantity; i++) {
            thingType = (int) (Math.random()*(ThingType.values().length));
            switch (thingType)
            {
                case 0:
                    things.add(new Food(FoodTitles.randomFoodTitle(), (int) (Math.random() * 100) + 1));
                    break;
                case 1:
                    things.add(new Container(ContainerTitles.randomContainerTitle(), (int) ((Math.random() * 100) + 1)));
                    break;
                default:
                    things.add(new Food(FoodTitles.randomFoodTitle(), (int) (Math.random() * 100) + 1));
                    break;
            }

        }

        //making random NPCs
        NPCs = new Vector<>();
        for (int i = 0; i < NPCquantity; i++) {
            NPCs.add(new Person(Names.randomName(), (int) (Math.random() * 100) + 1, (int) (Math.random() * 100) + 1));
        }
    }

    protected void performStaticAction() {
        int randomNPC;
        int randomValue;
        //random static action performance
        randomNPC = (int) (Math.random() * (NPCs.size()));
        randomValue = (int) (Math.random() * ActionTypeStatic.values().length);

        ActionStatic actionStatic = new ActionStatic(randomValue, ActionTypeStatic.randomAction());

        //special block for FREEING
        if (actionStatic.getType() == ActionTypeStatic.FREEING){
            //looking for container
            int containerIndex = -1;
            for (int i = 0; i < NPCs.elementAt(randomNPC).inventorySize(); i++) {
                if (NPCs.elementAt(randomNPC).getThing(i).getType() == ThingType.CONTAINER) {
                    containerIndex = i;
                    break;
                }
            }
            //checking if was found
            if (containerIndex != -1){
                Container container = (Container) NPCs.elementAt(randomNPC).getThing(containerIndex);
                //checking if not empty, so there was someone to free
                if (!container.isEmptyPerson()) {

                    NPCs.add(container.getPrisoner());

                    NPCs.elementAt(NPCs.indexOf(container.getPrisoner())).makeFree();

                    //placing prisoner on map
                    int x = (int) (Math.random() * (map.getSize()));
                    int y = (int) (Math.random() * (map.getSize()));

                    while (!map.pointIsEmptyPerson(x, y)) {
                        x = (int) (Math.random() * (map.getSize()));
                        y = (int) (Math.random() * (map.getSize()));
                    }
                    map.setPosition(NPCs.elementAt(NPCs.indexOf(container.getPrisoner())), x, y);

                    StringBuilder sb = new StringBuilder();
                    sb.append(NPCs.elementAt(randomNPC).getName()).append(" ").append(actionStatic.perform()).append(NPCs.elementAt(NPCs.indexOf(container.getPrisoner())).getName());

                    System.out.println(sb.toString());

                    NPCs.elementAt(randomNPC).useThing(container);
                }
            }
        } else{
            NPCs.elementAt(randomNPC).performAction(actionStatic);
        }

        System.out.println();
    }

    protected void performPersonAction(){
        int randomNPC;
        int randomVictim;
        int randomValue;
        //getting indexes of NPC and its victim
        randomNPC = (int) (Math.random() * (NPCs.size()));
        randomVictim = (int) (Math.random() * (NPCs.size()));
        while (randomNPC == randomVictim){
            randomNPC = (int) (Math.random() * (NPCs.size()));
            randomVictim = (int) (Math.random() * (NPCs.size()));
        }

        //moving to victim
        this.map.moveToPerson(NPCs.elementAt(randomNPC), NPCs.elementAt(randomVictim));

        //performing actions
        randomValue = (int) (Math.random() * 100);
        ActionPerson actionPerson = new ActionPerson(randomValue, ActionTypePerson.randomAction());
        this.NPCs.elementAt(randomNPC).performAction(actionPerson, this.NPCs.elementAt(randomVictim));

        System.out.println();

        //removing victim if dead or if it was prisoned
        if (NPCs.elementAt(randomVictim).getHP() == 0 || NPCs.elementAt(randomVictim).isPrisoner()){
            this.map.deleteElement(NPCs.elementAt(randomVictim));
            NPCs.remove(randomVictim);
        } else {
            //running away if action was bad
            if (actionPerson.getType() == ActionTypePerson.BEATING ||
                    actionPerson.getType() == ActionTypePerson.KICKING){
                NPCs.elementAt(randomVictim).runAwayFrom(this.map, NPCs.elementAt(randomNPC));
            }
        }
    }

    protected void duel(){
        System.out.println("Duel time!");

        if (NPCs.size() > 1) {

            //choosing who's attaking
            int attacker = (int) (Math.random() * 1.1);
            int defender;
            if (attacker == 1) {
                defender = 0;
            } else defender = 1;
            this.map.moveToPerson(NPCs.elementAt(attacker), NPCs.elementAt(defender));

            //printing some text
            StringBuilder sb = new StringBuilder();
            sb.append("Fighters are: ").append(NPCs.elementAt(attacker).getName()).append(": ").append(NPCs.elementAt(attacker).getHP()).append(" and ").append(NPCs.elementAt(defender).getName()).append(": ").append(NPCs.elementAt(defender).getHP());
            System.out.println(sb.toString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Let the Duel start!");

            //dueling
            int attackType, value, temp;
            while (NPCs.elementAt(attacker).getHP() > 0 && NPCs.elementAt(defender).getHP() > 0) {
                //performing attack
                attackType = (int) (Math.random());
                value = (int) (Math.random() * 100);
                switch (attackType) {
                    case 0:
                        NPCs.elementAt(attacker).performAction(new ActionPerson(value, ActionTypePerson.BEATING), NPCs.elementAt(defender));
                        break;
                    case 1:
                        NPCs.elementAt(attacker).performAction(new ActionPerson(value, ActionTypePerson.KICKING), NPCs.elementAt(defender));
                        break;
                }

                //making pause between actions
                System.out.println();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //changing roles
                temp = defender;
                defender = attacker;
                attacker = temp;
            }

            if (NPCs.elementAt(attacker).getHP() == 0) {
                NPCs.remove(attacker);
            } else {
                NPCs.remove(defender);
            }

            System.out.println("Duel is over. The winner is " + NPCs.elementAt(0).getName());

            System.exit(0);
        } else {
            System.out.println("No people for duel found! Game over!");
        }
    }

    protected void happyEnd(){
        StringBuilder sb = new StringBuilder();

        sb.append(NPCs.elementAt(0).getName());

        for (int i = 1; i < NPCs.size(); i++) {
            sb.append(" and ").append(NPCs.elementAt(i).getName());
        }

        sb.append(" decided to build a family and live a long happy life!");
        System.out.println(sb.toString());

        System.exit(0);
    }

    protected void action(){
        int x;
        int y;

        System.out.println(); //empty line to make the output look better

        //putting things on map
        for (int i = 0; i < this.things.size(); i++) {
            x = (int) (Math.random() * (map.getSize()));
            y = (int) (Math.random() * (map.getSize()));

            while (!map.pointIsEmptyThing(x, y)){
                x = (int) (Math.random() * (map.getSize()));
                y = (int) (Math.random() * (map.getSize()));
            }

            map.setPosition(things.get(i), x, y);
        }

        System.out.println(); //empty line to make the output look better
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //putting NPCs on map
        for (int i = 0; i < this.NPCs.size(); i++) {
            x = (int) (Math.random() * (map.getSize()));
            y = (int) (Math.random() * (map.getSize()));

            while (!map.pointIsEmptyPerson(x, y)){
                x = (int) (Math.random() * (map.getSize()));
                y = (int) (Math.random() * (map.getSize()));
            }

            map.setPosition(NPCs.get(i), x, y);
        }

        System.out.println(); //empty line to make the output look better
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        map.printMap(); //printing first state of map

        //performing random actions
        int randomActionType;
        while (this.NPCs.size() > 2){
            randomActionType = (int) (Math.random() * 10);

            switch (randomActionType)
            {
                case 0:
                    performStaticAction();
                    break;
                case 1:
                    performPersonAction();
                    break;
                case 2:
                    journey();
                    break;
                case 3:
                    movie();
                    break;
                default:
                    if (randomActionType > 5){
                        performPersonAction();
                    } else {
                        performStaticAction();
                    }
                    break;
            }

            //making pause between actions
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        map.printMap(); //printing last state of map

        //ending
        int randomEnd = (int) (Math.random()*2);
        switch (randomEnd)
        {
            default:
            case 0:
                duel();
                break;
            case 1:
                happyEnd();
                break;
        }
    }

    protected void journey(){
        System.out.println("Journey time!");

        int randomTransport = (int) (Math.random() * 4);

        Transport transport;
        int seats;

        //choosing transport and creating it
        switch (randomTransport){
            default:
            case 0:
                seats = (int) (Math.random() * 4 + 1);
                transport = new Car(seats, CarType.randomCarType());
                break;
            case 1:
                seats = (int) (Math.random() * 100 + 20);
                transport = new Ship(seats);
                break;
            case 2:
                seats = (int) (Math.random() * 8 + 3);
                int partsQuantity = (int) (Math.random() * 5 + 1);
                int partWeight = (int) (Math.random() * 100 + 30);
                transport = new Rocket(seats, partsQuantity, partWeight);
                break;
            case 3:
                seats = (int) (Math.random() * 100 + 20);
                transport = new Train(seats);
                break;
        }

        //getting in transport
        for (int i = 0; i < NPCs.size(); i++) {
            transport.getIn(NPCs.elementAt(i));
        }

        System.out.println();

        //choosing destination
        String[] destinations = {"Moon", "Earth", "Javaland"};
        int randomDestination = (int) (Math.random()*destinations.length);

        try {
            transport.go(destinations[randomDestination]);
        } catch (NoPersonException e) {
            System.out.println("How can " + transport.getModel() + "be driven without the driver?");
        }

        //creating new map == place where we arrived
        int randomSizeOfMap = (int) (Math.random() * 10 + 4);
        map = new Map(randomSizeOfMap);

        //maiking random things
        int thingsQuantity = (int) (Math.random() * (randomSizeOfMap-1) + 2);
        int thingType;
        things = new Vector<>();
        for (int i = 0; i < thingsQuantity; i++) {
            thingType = (int) (Math.random()*(ThingType.values().length));
            switch (thingType)
            {
                case 0:
                    things.add(new Food(FoodTitles.randomFoodTitle(), (int) (Math.random() * 100) + 1));
                    break;
                case 1:
                    things.add(new Container(ContainerTitles.randomContainerTitle(), (int) ((Math.random() * 100) + 1)));
                    break;
                default:
                    things.add(new Food(FoodTitles.randomFoodTitle(), (int) (Math.random() * 100) + 1));
                    break;
            }
        }

        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //getting out of the car
        NPCs.clear();
        NPCs.addAll(transport.getPassengers());
        NPCs.add(transport.getDriver());
        transport.leaveAll();

        //continuing action
        action();
    }

    protected void movie(){
        System.out.println("Movie time!");
        //declaring movies and choosing one and its duration
        String[] movies = {"Arrival", "Fargo", "The Crow", "Taxi", "Tenet"};
        int randomMovie = (int) (Math.random()* movies.length);
        int duration = (int) (Math.random()*2 + 1);

        Cinema cinema = new Cinema(NPCs.size(),movies[randomMovie],duration);

        cinema.enter(NPCs);
        cinema.watch();
        cinema.exit();
    }

    public void runWithParameters(int sizeOfMap, int NPCquantity, int thingsQuantity) throws InvalidParametrsRunTimeException {
        if (NPCquantity < 2 || sizeOfMap <= 0){
            throw new InvalidParametrsRunTimeException("Invalid data!");
        } else {
            createModel(sizeOfMap, NPCquantity, thingsQuantity);

            action();
        }
    }

    public void runRandom(){
        //creating map
        int sizeOfMap = (int) (Math.random() * 10 + 4);

        //creating NPCs
        int NPCquantity = (int) (Math.random() * (sizeOfMap-1) + 2);

        //creating things
        int thingsQuantity = (int) (Math.random() * (sizeOfMap-1) + 2);

        createModel(sizeOfMap, NPCquantity, thingsQuantity);

        action();
    }
}
