package main.client.controller;

import main.model.EmotionMessageBean;
import main.utils.ConnectionConstants;
import org.glassfish.tyrus.client.ClientManager;

import javax.swing.*;
import javax.websocket.Session;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Observable;
import java.util.Observer;

/**
 * Server connection panel logic
 *
 * @author Jason Rice
 * @version 1.0
 */
public class ServerConnectionPanelLogic implements Observer{
    JTextField ipAddressTextField;
    JTextField portTextField;
    JButton startStopButton;
    JLabel timeStampValueLabel;
    ClientEndpoint clientEndPoint;
    Session session = null;
    EmotionMessageBean emotionMessageBean;
    
    /**
     * Constructor sets the references to the fields, end point, and the message bean.
     * @param clientEndPoint
     * @param emotionMessageBean
     * @param ipAddressTextField
     * @param portTextField
     * @param startStopButton
     * @param timeStampValueLabel
     */
    public ServerConnectionPanelLogic(
            ClientEndpoint clientEndPoint, 
            EmotionMessageBean emotionMessageBean,
            JTextField ipAddressTextField, 
            JTextField portTextField, 
            JButton startStopButton, 
            JLabel timeStampValueLabel){
        
        this.clientEndPoint = clientEndPoint;
        this.ipAddressTextField = ipAddressTextField;
        this.portTextField = portTextField;
        this.timeStampValueLabel = timeStampValueLabel;
        this.emotionMessageBean = emotionMessageBean;
        startStopButton.addActionListener(generateStartServerConnectionActionListener());
        this.startStopButton = startStopButton;
    }
    
    /**
     * ActionListener for the connection logic.
     */
    public ActionListener generateStartServerConnectionActionListener(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String portCheck = (portTextField != null 
                            && !portTextField.getText().equals(""))? portTextField.getText() : "1726";
                    String ipCheck = (ipAddressTextField != null 
                            && !ipAddressTextField.getText().equals(""))? ipAddressTextField.getText() : "localhost";
                    
                    String sURI = "ws://"; 
                    sURI += ipCheck;
                    sURI += ':'; 
                    sURI += portCheck;
                    sURI += "/" + ConnectionConstants.ROOT_PATH + "/" + ConnectionConstants.ENDPOINT_PATH;
                    
                    ClientManager client = ClientManager.createClient();
                    
                    if(startStopButton.getText().equals("Connect")){
                        int inputErrorCheck = 0;
                        
                        if(!portCheck.equals("1726")){
                            for(int number = 0; number < portCheck.length(); number++){
                                if(portCheck.charAt(number) < 48 || portCheck.charAt(number) > 57){
                                    inputErrorCheck = 1;
                                }
                            }
                        }
                        
                        if(!ipCheck.equals("localhost")){
                            for(int number = 0; number < ipCheck.length(); number++){
                                if((ipCheck.charAt(number) < 48 && ipCheck.charAt(number) != 46) 
                                        || ipCheck.charAt(number) > 57){
                                    inputErrorCheck = 2;
                                }
                            }
                        }
                        
                        if(inputErrorCheck == 1){
                            JOptionPane.showMessageDialog(
                                    timeStampValueLabel.getRootPane(), "Invalid port entered.", 
                                    "Port Error", JOptionPane.WARNING_MESSAGE);
                        }else if(inputErrorCheck == 2){
                            JOptionPane.showMessageDialog(
                                    timeStampValueLabel.getRootPane(), "Invalid ip address entered.", 
                                    "IP Error", JOptionPane.WARNING_MESSAGE);
                        }else{
                            startStopButton.setText("Disconnect");
                            session = client.connectToServer(clientEndPoint, new URI(sURI));
                        }
                    }else{
                        session.close();
                        startStopButton.setText("Connect");
                    }
                }catch(Exception e) {
                    String message = "Connection could not be established.";
                    JOptionPane.showMessageDialog(
                            timeStampValueLabel.getRootPane(), message, 
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                    startStopButton.setText("Connect");
                }
            }
        };
    }

    /**
     * update the time stamp.
     *
     * @param observable
     * @param arg
     */
    @Override
    public void update(Observable observable, Object arg) {
        if (this.emotionMessageBean == observable) {
            this.timeStampValueLabel.setText("" + this.emotionMessageBean.getClockTick());
        }
    }
}
