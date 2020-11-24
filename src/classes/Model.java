package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.ActionType;
import classes.enums.Names;

import java.util.Vector;

public class Model {
    private Map map;
    private Vector<Person> NPCs;
    private Vector<Thing> things;
    private Vector<Action> actions;

    public Model() {
    }

    public void runRandom(){
        //creating map
        int mapSize = (int) (Math.random() * 10 + 4);
        map = new Map(mapSize);

        //creating NPCs
        int NPCquantity = (int) (Math.random() * (mapSize-1) + 2);
        NPCs = new Vector<>();
        for (int i = 0; i < NPCquantity; i++) {
            NPCs.add(new Person(Names.randomName()));
        }

        //putting NPCs on map
        int x;
        int y;
        for (int i = 0; i < NPCquantity; i++) {
            x = (int) (Math.random() * (mapSize-1));
            y = (int) (Math.random() * (mapSize-1));

            while (!map.pointIsEmpty(NPCs.get(i), x, y)){
                x = (int) (Math.random() * (mapSize-1));
                y = (int) (Math.random() * (mapSize-1));
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
            actions.add(new Action(actionValue, ActionType.randomAction()));
        }

        //adding actions to NPCs
        int randomAction;
        int randomStart = (int) (Math.random() * (NPCquantity-2));
        int randomNumberOfCycles = (int) (Math.random() * 3 + 1);

        for (int i = 0; i < randomNumberOfCycles; i++) {
            for (int j = randomStart; j < NPCquantity; j++) {
                randomAction = (int) (Math.random() * anctionsQuantity);
                NPCs.elementAt(j).addAction(actions.elementAt(randomAction));
            }
        }

        //random performing actions
        int randomPerformActionCycles = (int) (Math.random() * 20);
        int randomNPC;
        int randomNPCAction;
        for (int i = 0; i < randomPerformActionCycles; i++) {
            randomNPC = (int) (Math.random() * (NPCquantity));
            if (NPCs.elementAt(randomNPC).getActionsSize() > 0) {
                randomNPCAction = (int) (Math.random() * (NPCs.elementAt(randomNPC).getActionsSize() - 1));
                NPCs.elementAt(randomNPC).performAction(NPCs.elementAt(randomNPC).getAction(randomNPCAction));
            }
        }
    }
}
