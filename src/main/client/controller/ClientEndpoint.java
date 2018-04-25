package main.client.controller;

import main.model.EmotionMessageBean;
import main.model.MessageDecoder;
import main.model.MessageEncoder;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * ClientEndpoint
 *
 * @author Jason Rice, Balachandar Sampath
 * @version 1.1
 */
@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ClientEndpoint {
    EmotionMessageBean emotionMessageBean;

    /**
     * Constructor sets the emotionMessageBean reference.
     *
     * @param emotionMessageBean
     */
    public ClientEndpoint(EmotionMessageBean emotionMessageBean) {
        this.emotionMessageBean = emotionMessageBean;
    }

    /**
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
    }

    /**
     * @param message
     */
    @OnMessage
    public void onMessage(EmotionMessageBean message) {


        if (message.getAffective() != null) {
            this.emotionMessageBean.setAffective(message.getAffective());
        }
        if (message.getExpressive() != null) {
            this.emotionMessageBean.setExpressive(message.getExpressive());
        }
        if (message.getClockTick() != 0.0) {
            this.emotionMessageBean.setTick(message.getClockTick());
        }
        if (emotionMessageBean.hasChanged()) {
            this.emotionMessageBean.notifyObservers();
        }
    }

}
