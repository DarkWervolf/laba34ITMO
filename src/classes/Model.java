package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.*;

import java.util.Vector;

public class Model {
    private Map map;
    private Vector<Person> NPCs;
    private Vector<Thing> things;
    private Vector<Action> actions;

    public Model() {
    }

    protected void performStaticAction() {
        int randomNPC;
        int randomValue;
        //random static action performance
        randomNPC = (int) (Math.random() * (NPCs.size()));
        randomValue = (int) (Math.random() * ActionTypeStatic.values().length);
        this.NPCs.elementAt(randomNPC).performAction(new ActionStatic(randomValue, ActionTypeStatic.randomAction()));
        System.out.println();

        //making pause between actions
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        //introducing variables
        int xNPC, yNPC, xVictim, yVictim;
        xNPC = map.getPosition(NPCs.elementAt(randomNPC))[0]; //getting coordinates of NPC
        yNPC = map.getPosition(NPCs.elementAt(randomNPC))[1];
        xVictim = map.getPosition(NPCs.elementAt(randomVictim))[0]; //getting coordinates of victim
        yVictim = map.getPosition(NPCs.elementAt(randomVictim))[1];
        //checking if victim is near and moving NPC if necessary
        if (!(Math.abs(xNPC - xVictim) < 2 && Math.abs(xNPC-yVictim)<2)){
            int newX = xNPC; //making new coordinates
            int newY = yNPC;
            while (!(Math.abs(newX - xVictim) < 2 && Math.abs(newY-yVictim)<2 && map.pointIsEmptyPerson(newX, newY))){ //moving to victim, if not near
                newX = (int) (Math.random() * (map.getSize()-1) + (xVictim-1));
                newY = (int) (Math.random() * (map.getSize()-1) + (yVictim-1));
            }
            NPCs.elementAt(randomNPC).move(this.map, newX, newY); //moving NPC
            this.map.printMap();
        }
        //performing actions
        randomValue = (int) (Math.random() * ActionTypePerson.values().length);
        this.NPCs.elementAt(randomNPC).performAction(new ActionPerson(randomValue, ActionTypePerson.randomAction()), this.NPCs.elementAt(randomVictim));
        System.out.println();

        //making pause between actions
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runRandom(){
        //creating map
        int mapSize = (int) (Math.random() * 10 + 4);
        map = new Map(mapSize);

        //creating NPCs
        int NPCquantity = (int) (Math.random() * (map.getSize()-1) + 2);
        NPCs = new Vector<>();
        for (int i = 0; i < NPCquantity; i++) {
            NPCs.add(new Person(Names.randomName()));
        }

        //putting NPCs on map
        int x;
        int y;
        for (int i = 0; i < NPCquantity; i++) {
            x = (int) (Math.random() * (map.getSize()-1));
            y = (int) (Math.random() * (map.getSize()-1));

            while (!map.pointIsEmptyPerson(x, y)){
                x = (int) (Math.random() * (map.getSize()-1));
                y = (int) (Math.random() * (map.getSize()-1));
            }

            map.setPosition(NPCs.get(i), x, y);
        }

        map.printMap(); //printing first state of map

        //performing random actions
        int randomCyclesQuantity = (int) (Math.random() * 30 + 1);
        int randomActionType;
        for (int i = 0; i < randomCyclesQuantity; i++) {
            randomActionType = (int) (Math.random() * 2);
            switch (randomActionType)
            {
                case 0:
                    performStaticAction();
                    break;
                case 1:
                    performPersonAction();
                    break;
                default: break;
            }
        }

        map.printMap(); //printing last state of map
    }
}
