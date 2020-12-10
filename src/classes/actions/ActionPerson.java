package classes.actions;

import classes.Emotion;
import classes.Map;
import classes.Person;
import classes.abstracts.Action;
import classes.enums.ActionTypePerson;
import classes.enums.ActionTypeStatic;
import classes.enums.EmotionType;
import classes.enums.ThingType;
import classes.things.Container;

public class ActionPerson extends Action {

    ActionTypePerson type;

    public ActionPerson(int value, ActionTypePerson type) {
        super(value);
        this.type = type;
    }

    public ActionTypePerson getType() {
        return type;
    }

    public void perform(Person person, Person victim){
        //printing action
        StringBuilder sb = new StringBuilder();
        sb.append(person.getName()).append(this.type.outStringAction()).append(" ").append(victim.getName());
        System.out.println(sb.toString());

        //reaction depended on action
        switch (this.getType())
        {
            case PRISONING:
                //looking for container
                int containerIndex = -1;
                for (int i = 0; i < person.inventorySize(); i++) {
                    if (person.getThing(i).getType() == ThingType.CONTAINER){
                        containerIndex = i;
                        break;
                    }
                }

                if (containerIndex != -1){
                    //prisoning if there is container
                    Container container = (Container) person.getThing(containerIndex);
                    if (container.isEmptyPerson()){
                        person.useThingOn(container, victim);
                        victim.setEmotion(new Emotion(this.getValue(), EmotionType.ANGRY));
                        victim.makePrisoner();
                    } else {
                        victim.say("Damn, you already put someone in there...");
                    }
                }else{
                    victim.say("Lol, you're such a fool, don't even have a cage to put me in!");
                }
                break;
            case EATING:
                victim.say("GOD, I'M BEING EATEN, HELP ME");
                victim.goNuts();
                victim.changeHP(-victim.getHP());
                break;
            case BEATING:
            case KICKING:
                victim.changeHP(-this.getValue());
                if (this.getValue() == 0){
                    System.out.println(person.getName() + " missed.");
                    victim.performAction(new ActionStatic(0, ActionTypeStatic.LAUTHING));
                    victim.say("LMAO, you're such a well-aimed fighter!");
                }
                if (victim.getHP() > 0) {
                    //victim emotions
                    if (this.getValue() > 50) {
                        victim.setEmotion(new Emotion(this.getValue(), EmotionType.SAD));
                    } else {
                        victim.setEmotion(new Emotion(this.getValue(), EmotionType.ANGRY));
                    }
                }
                break;
            case KISSING:
            case HUGGING:
                victim.changeHP(this.getValue());
                if (this.getValue() > 50){
                    victim.setEmotion(new Emotion(this.getValue(), EmotionType.HAPPY));
                } else{
                    victim.setEmotion(new Emotion(this.getValue(), EmotionType.NEUTRAL));
                }
                break;
            default:
                victim.randomEmotion();
                System.out.println(victim.getName() + ": What the hell just happend?");
        }
    }
}
