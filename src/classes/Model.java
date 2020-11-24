package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.ActionTypeStatic;
import classes.enums.Names;

import java.util.Vector;

public class Model {
    private Map map;
    private Vector<Person> NPCs;
    private Vector<Thing> things;
    private Vector<Action> actions;

    public Model() {
    }

    protected void performStaticActions() {
        int randomPerformActionCycles = (int) (Math.random() * 20);
        int randomNPC;
        int randomValue;
        for (int i = 0; i < randomPerformActionCycles; i++) {
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
    }

    protected void performPersonActions(){
        int randomPerformActionCycles = (int) (Math.random() * 20);
        int randomNPC;
        int randomVictim;
        int randomValue;
        for (int i = 0; i < randomPerformActionCycles; i++) {
            //getting indexes of NPC and its victim
            randomNPC = (int) (Math.random() * (NPCs.size()));
            randomVictim = (int) (Math.random() * (NPCs.size()));
            while (randomNPC == randomVictim){
                randomNPC = (int) (Math.random() * (NPCs.size()));
                randomVictim = (int) (Math.random() * (NPCs.size()));
            }

            //checking if victim is near
            int xNPC, yNPC, xVictim, yVictim;
            xNPC = map.getPosition(NPCs.elementAt(randomNPC))[0]; //getting coordinates of NPC
            yNPC = map.getPosition(NPCs.elementAt(randomNPC))[1];
            xVictim = map.getPosition(NPCs.elementAt(randomVictim))[0]; //getting coordinates of victim
            yVictim = map.getPosition(NPCs.elementAt(randomVictim))[1];
            int newX = xNPC; //making new coordinates
            int newY = yNPC;
            while (!(Math.abs(newX - xVictim) < 2 && Math.abs(newY-yVictim)<2 && map.pointIsEmptyPerson(newX, newY))){ //moving to victim, if not near
                newX = (int) (Math.random() * (map.getSize()-1) + (xVictim-1));
                newY = (int) (Math.random() * (map.getSize()-1) + (yVictim-1));
            }
            NPCs.elementAt(randomNPC).move(this.map, newX, newY); //moving NPC


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
        map.printMap();

        //creating actions
        actions = new Vector<>();
        int anctionsQuantity = (int) (Math.random() * (NPCquantity-1) + 2);
        int actionValue;
        for (int i = 0; i < anctionsQuantity; i++) {
            actionValue = (int) (Math.random() * 10);
            actions.add(new ActionStatic(actionValue, ActionTypeStatic.randomAction()));
        }

        //random performing static actions
        //performStaticActions();
        performPersonActions();
    }
}
