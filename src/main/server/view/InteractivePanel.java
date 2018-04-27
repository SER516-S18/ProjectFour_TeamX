package main.server.view;

import main.server.view.components.XSpinner;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractivePanel extends JPanel {

    private final String btnSendValue = "Send";
    private final String btnStartValue = "Start";
    private final String btnStopValue = "Stop";
    private JButton btnSend;
    private JCheckBox chckbxAutoReset;
    private JSpinner emoStateSpinner;
    private boolean isAutoReset = false;
    private boolean isStarted = false;


    /**
     * Constructor is adding the interactivePanel to server UI
     */
    public InteractivePanel() {

        //this.setBackground(Color.GRAY);
        this.setBorder(new TitledBorder("Interactive"));

        chckbxAutoReset = new JCheckBox("Auto Reset");
        chckbxAutoReset.addActionListener(new autoResetListener());
        emoStateSpinner = new XSpinner(0.25,100,0.25);
        emoStateSpinner.addChangeListener(new spinnerListener());
        btnSend = new JButton(btnSendValue);
        btnSend.addActionListener(new buttonListener());
        this.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        this.add(new JLabel("<html>EmoState Interval:</html>"));
        this.add(emoStateSpinner);
        this.add(chckbxAutoReset);
        this.add(btnSend);
    }

    private class autoResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox changedObj = (JCheckBox) e.getSource();
            isAutoReset = changedObj.isSelected();
            if(isAutoReset){
                btnSend.setText(btnStartValue);
            } else {
                btnSend.setText(btnSendValue);
            }
        }
    }

    private class buttonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(isAutoReset && !isStarted){
                isStarted = true;
                btnSend.setText(btnStopValue);
                chckbxAutoReset.setEnabled(false);
                //TODO: start transmitting
            } else if(isAutoReset && isStarted){
                isStarted = false;
                btnSend.setText(btnStartValue);
                chckbxAutoReset.setEnabled(true);
                //TODO: stop transmitting
            } else {
                //TODO: transmit once
            }
        }
    }

    private class spinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

            if(isStarted){
                //TODO: change transmission period
            }
        }
    }
}
