package classes.actions;

import classes.Emotion;
import classes.Person;
import classes.abstracts.Action;
import classes.enums.ActionTypePerson;
import classes.enums.ActionTypeStatic;
import classes.enums.EmotionType;

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

                    System.out.println(); //empty line to make the output look better

                    //victim healing
                    if (victim.inventorySize() != 0){
                        int thing = (int) (Math.random()*victim.inventorySize());
                        victim.useThing(victim.getThing(thing));
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
