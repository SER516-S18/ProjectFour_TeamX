package main.server.controller;

import main.model.ConsoleMessage;
import main.model.EmotionMessageBean;
import main.model.MessageDecoder;
import main.model.MessageEncoder;
import main.server.view.ConsolePanel;
import main.utils.ConnectionConstants;

import javax.swing.text.BadLocationException;
import javax.websocket.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Web Socket Endpoint which handles the various lifecycle of connections with client.
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
@javax.websocket.server.ServerEndpoint(value = "/" + ConnectionConstants.ENDPOINT_PATH,
        encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ServerEndpoint {

    public static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());

    /**
     * This method is called when connection opens
     *
     * @param session This is the first parameter to onOpen method and
     *                holds client session
     */
    @OnOpen
    public void onOpen(Session session) {
        /*try {
            ConsolePanel.setMessage(String.format("%s connected", session.getId()));
        } catch (BadLocationException ex) {
            System.out.println(ex.getMessage());
        }*/
        //ConsoleMessage.setMess
        clients.add(session);
    }

    /**
     * This method is called when connection opens
     *
     * @param message This is the first parameter to onMessage
     *                method and holds the message object received
     * @param session This is the second parameter to onMessage
     *                method and holds client session
     */
    @OnMessage
    public void onMessage(EmotionMessageBean message, Session session) {
        String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            session.getUserProperties().put("user", message.getSender());
        }
        /*try {
            ConsolePanel.setMessage(String.format("[%s:%s]", session.getId(), message.toString()));
        } catch (BadLocationException ex) {
            System.out.println(ex.getMessage());
        }*/

    }

    /**
     * This method is called when connection closes
     *
     * @param session This is the first parameter to onClose method
     *                and holds client session
     */
    @OnClose
    public void onClose(Session session) {
        /*try {
            ConsolePanel.setMessage(String.format("%s disconnected the connection", session.getId()));
        } catch (BadLocationException ex) {
            System.out.println(ex.getMessage());
        }*/
        clients.remove(session);
    }

    /**
     * This method is called when there is connection error
     *
     * @param session   This is the first parameter to onError method
     *                  and holds client session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        //ConsolePanel.setMessage(throwable.getMessage());
        /*try {
            ConsolePanel.setErrorMessage(throwable.getMessage());
        } catch (BadLocationException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

}