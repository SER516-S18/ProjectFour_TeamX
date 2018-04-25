package main.server.controller;

import main.model.EmotionMessageBean;
import main.server.view.InteractivePanel;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller which acts as a interface between server and UIController. This
 * class has to be created first when the main.server ui or main.client ui is on
 * and should be the first one to be invoked
 *
 * @author Balachandar Sampath, Rhythm Sharma
 * @version 1.0
 */
public class EndpointController {

    private EmotionMessageBean emotionMessageBean = null;
    private Timer timer = new Timer("Timer");

    private EndpointController() {
    }

    /**
     * Returns an instance of the class
     *
     * @return EndpointController
     */
    public static EndpointController getInstance() {

        return SingletonHolder.endpointController;
    }

    public EmotionMessageBean getEmotionMessageBean() {
        return emotionMessageBean;
    }

    /**
     * Updates blink in the message bean for the expression
     *
     * @param blink If blink is set
     */
    public void updateExpBlink(boolean blink) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setBlink(blink);
    }

    /**
     * Updates right wink in the message bean for the expression
     *
     * @param rightWink If right wink is set
     */
    public void updateExpRightWink(boolean rightWink) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setRightWink(rightWink);
    }

    /**
     * Updates left wink in the message bean for the expression
     *
     * @param leftWink If left wink is set
     */
    public void updateExpLeftWink(boolean leftWink) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setLeftWink(leftWink);
    }

    /**
     * Updates looking left in the message bean for the expression
     *
     * @param lookingLeft value of looking left
     */
    public void updateExpLookingLeft(double lookingLeft) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setLookingLeft(lookingLeft);
    }

    /**
     * Updates looking right in the message bean for the expression
     *
     * @param lookingRight value of looking right
     */
    public void updateExpLookingRight(double lookingRight) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setLookingRight(lookingRight);
    }

    /**
     * Updates eye brow raise in the message bean for the expression
     *
     * @param eyeBrowRaise value of eye brow raise
     */
    public void updateEyeBrowRaise(double eyeBrowRaise) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setRaiseBrow(eyeBrowRaise);
    }

    /**
     * Updates eyes brow furrow in the message bean for the expression
     *
     * @param eyeBrowFurrow value of looking eyes open
     */
    public void updateEyeBrowFurrow(double eyeBrowFurrow) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setFurrowBrow(eyeBrowFurrow);
    }

    /**
     * Updates smile in the message bean for the expression
     *
     * @param smile value of smile
     */
    public void updateSmile(double smile) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setSmile(smile);
    }

    /**
     * Updates clench in the message bean for the expression
     *
     * @param clench value of clench
     */
    public void updateClench(double clench) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setClench(clench);
    }

    /**
     * Updates looking up in the message bean for the expression
     *
     * @param lookingUp value of looking up
     */
    public void updateLookingUp(double lookingUp) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setLookingUp(lookingUp);
    }

    /**
     * Updates looking down in the message bean for the expression
     *
     * @param lookingDown value of looking down
     */
    public void updateLookingDown(double lookingDown) {
        EndpointController.getInstance().emotionMessageBean.
                getExpressive().setLookingDown(lookingDown);
    }

    /**
     * Updates interest parameter in the message bean for the expression
     *
     * @param interest value of interest
     */
    public void updateInterest(double interest) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setInterest(interest);
    }

    /**
     * To update the clock
     *
     * @param time
     */
    public void updateClock(double time) {
        EndpointController.getInstance().emotionMessageBean.setClockTick(time);
    }

    /**
     * Updates engagement parameter in the message bean for the expression
     *
     * @param engagement value of engagement
     */
    public void updateEngagement(double engagement) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setEngagement(engagement);
    }

    /**
     * Updates stress parameter in the message bean for the expression
     *
     * @param stress value of stress
     */
    public void updateStress(double stress) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setStress(stress);
    }

    /**
     * Updates relaxation parameter in the message bean for the expression
     *
     * @param relaxation value of relaxation
     */
    public void updateRelaxation(double relaxation) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setRelaxation(relaxation);
    }

    /**
     * Updates excitement parameter in the message bean for the expression
     *
     * @param excitement value of excitement
     */
    public void updateExcitement(double excitement) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setExcitement(excitement);
    }

    /**
     * Updates focus parameter in the message bean for the expression
     *
     * @param focus value of focus
     */
    public void updateFocus(double focus) {
        EndpointController.getInstance().emotionMessageBean.
                getAffective().setFocus(focus);
    }

    /**
     * This method is to send the message only once
     */
    public void sendOnce() {
        sendEmotionMessage();
    }

    /**
     * This method is called to send message in certain given intervals of time
     *
     * @param intervals This interval tells the value of intervals between sending
     *                  messages to client from server
     */
    public void sendInIntervals(double intervals) {
        long period = (long) (intervals * 1000L);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                sendEmotionMessage();
                long period1 = (long) (InteractivePanel.getEmoStateTimeInterval() * 1000L);
                if(period!=period1) {
                    timer.cancel();
                    sendInIntervals(InteractivePanel.getEmoStateTimeInterval());
                }
            }
        }, 0, period);
    }

    /**
     * This method is to stop the message sending
     */
    public void stop() {
        timer.cancel();
    }

    /**
     * This method is invoked to send emotion message
     */
    public void sendEmotionMessage() {
        // Reintialisation emotion for resetting data
        this.emotionMessageBean = new EmotionMessageBean();

        UIController.getInstance().updateMessageBeanFromUI();
        for (Session client : ServerEndpoint.clients) {
            // do not resend the message to its sender
            try {
                client.getBasicRemote().sendObject(emotionMessageBean);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This will support lazy initialisation of endpointController
     */
    private static class SingletonHolder {
        public static final EndpointController
                endpointController = new EndpointController();
    }
}
