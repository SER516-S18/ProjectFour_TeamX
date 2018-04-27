package main.model;

import java.util.Observable;

/**
 * Bean which stores the emotion message to be sent to client.
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
public class EmotionMessageBean extends Observable {

    private ExpressiveBean expressive;
    private String sender;
    private AffectiveBean affective;
    private double clockTick;

    public EmotionMessageBean() {
        expressive = new ExpressiveBean();
        affective = new AffectiveBean();
    }

    /**
     * Getter/Setter for class properties
     */

    public ExpressiveBean getExpressive() {
        return expressive;
    }

    /**
     * sets the expressive bean and sets changed to true.
     *
     * @param expressive
     */
    public void setExpressive(ExpressiveBean expressive) {
        this.expressive = expressive;
        this.setChanged();
    }

    public AffectiveBean getAffective() {
        return affective;
    }

    /**
     * sets the affective bean and sets changed to true.
     *
     * @param affective
     */
    public void setAffective(AffectiveBean affective) {
        this.affective = affective;
        this.setChanged();
    }

    public String getSender() {
        return sender;
    }

    /**
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * set the tick in the client and sets changed to true.
     *
     * @param tick
     */
    public void setTick(double tick) {
        clockTick = tick;
        this.setChanged();
    }

    public double getClockTick() {
        return clockTick;
    }

    /**
     * @param clockTickVal
     */
    public void setClockTick(double clockTickVal) {
        clockTick = clockTickVal;
    }

    /**
     * This method is used to generate a Stringified version of the class properties
     */
    @Override
    public String toString() {
        return "EmotionMessageBean{" +
                "expressive=" + expressive +
                ", sender='" + sender + '\'' +
                ", affective=" + affective +
                ", clock=" + clockTick +
                '}';
    }
}
