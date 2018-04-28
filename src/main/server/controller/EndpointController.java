package main.server.controller;

import main.model.EmotionMessageBean;
import main.model.MessageContolBean;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller which acts as a interface between server and EndPointController. This
 * class has to be created first when the main.server ui or main.client ui is on
 * and should be the first one to be invoked
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
public class EndpointController {

    private MessageContolBean messageControlBean = null;
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

    public void setMessageControlBean(MessageContolBean messageControlBean) {
        this.messageControlBean = messageControlBean;
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
                long period1 = (long) (messageControlBean.getInterval() * 1000L);
                if(period!=period1) {
                    timer.cancel();
                    sendInIntervals(messageControlBean.getInterval());
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

        for (Session client : ServerEndpoint.clients) {
            // do not resend the message to its sender
            try {
                double clockTick = messageControlBean.getEmotionMessageBean().getClockTick() + messageControlBean.getInterval();
                messageControlBean.getEmotionMessageBean().setClockTick(clockTick);

                messageControlBean.setClockTick(clockTick);
                client.getBasicRemote().sendObject(messageControlBean.getEmotionMessageBean());
                if(messageControlBean.isEyeActivated() && !messageControlBean.isEyeAutoReset()){
                    messageControlBean.setValue(messageControlBean.getCurrentEyeItem(),0);
                } else if(messageControlBean.isEyeActivated() && messageControlBean.isEyeAutoReset()) {
                    int val = messageControlBean.getEyeVal(messageControlBean.getCurrentEyeItem()) ? 0 : 1;
                    messageControlBean.setValue(messageControlBean.getCurrentEyeItem(),val);
                }

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
