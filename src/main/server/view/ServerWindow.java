package main.server.view;


import javax.swing.*;
import java.awt.*;

/**
 * ServerWindowrepresent the server JFrame.
 * Consists of 3 panels Interactive, Detection and Console Panel.
 *
 * @author Ejaz
 * @version 1.0
 */
public class ServerWindow extends JFrame {

    public ServerWindow(InteractivePanel interactivePanel, DetectionPanel detectionPanel, ConsolePanel consolePanel) {
        this.setTitle("GroupX Server");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(interactivePanel);
        this.getContentPane().add(detectionPanel);
        this.getContentPane().add(consolePanel);
    }
}
