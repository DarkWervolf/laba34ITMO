package classes;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.*;

import java.util.Random;
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

        //moving to victim
        this.map.moveToPerson(NPCs.elementAt(randomNPC), NPCs.elementAt(randomVictim));

        //performing actions
        randomValue = (int) (Math.random() * 100);
        this.NPCs.elementAt(randomNPC).performAction(new ActionPerson(randomValue, ActionTypePerson.randomAction()), this.NPCs.elementAt(randomVictim));
        System.out.println();

        //removing victim if dead
        if (NPCs.elementAt(randomVictim).getHP() == 0){
            this.map.deleteElement(NPCs.elementAt(randomVictim));
            NPCs.remove(randomVictim);
        }

        //making pause between actions
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void duel(){
        //choosing who's attaking
        int attacker = (int) Math.random();
        int defender;
        if (attacker == 1){
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
        while (NPCs.elementAt(attacker).getHP() > 0 && NPCs.elementAt(defender).getHP() > 0){
            //performing attack
            attackType = (int) (Math.random());
            value = (int) (Math.random() * 100);
            switch (attackType)
            {
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

        if (NPCs.elementAt(attacker).getHP() == 0){
            NPCs.remove(attacker);
        } else {
            NPCs.remove(defender);
        }

        System.out.println("Duel is over. The winner is " + NPCs.elementAt(0).getName());
    }

    protected void action(){
        //putting NPCs on map
        int x;
        int y;
        for (int i = 0; i < this.NPCs.size(); i++) {
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
        int randomActionType;
        while (this.NPCs.size() > 2){
            randomActionType = (int) (Math.random() * 2);
            if (this.NPCs.size() < 3){
                break;
            }
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

        duel();
    }

    public void runWithParameters(int sizeOfMap, int NPCquantity){
        if (NPCquantity == 1){
            Person alone = new Person(Names.randomName(), (int) (Math.random() * 100) + 1);
            alone.say("I'm so alone here...");
            alone.setEmotion(new Emotion(((int) (Math.random() * 100) + 1), EmotionType.SAD));
            alone.say("I don't wanna live anymore...");
            alone.changeHP(-alone.getMaxHP());
        } else if (NPCquantity <= 0){
            System.out.println("Please, set at least one player!");
        } else if (sizeOfMap < 4){
            System.out.println("Please, set the size of map at least 4!");
        } else if (NPCquantity > Math.pow(sizeOfMap,2) - 2){
            System.out.println("PLease, choose different quantity of players - it must be no more than size of map squared - 2");
        } else {

            //initializing variables
            map = new Map(sizeOfMap);
            NPCs = new Vector<>();
            for (int i = 0; i < NPCquantity; i++) {
                NPCs.add(new Person(Names.randomName(), (int) (Math.random() * 100) + 1));
            }

            action();
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
            NPCs.add(new Person(Names.randomName(), (int) (Math.random() * 100) + 1));
        }

        action();
    }
}
