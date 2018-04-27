package main.server.view.components;

import main.model.EmotionMessageBean;
import main.server.view.DetectionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EyeControl extends JPanel {

    private JComboBox<String> itemComboBox;
    private String currentItem;
    private final String btnActivateValue = "Activate";
    private final String btnStartValue = "Start";
    private final String btnStopValue = "Stop";
    private JButton btnSend;
    private JCheckBox chckbxAutoReset;
    private boolean isAutoReset = false;
    private boolean isStarted = false;
    private EmotionMessageBean emotionMessageBean;

    public EyeControl(String name, String[] items, EmotionMessageBean bean){

        emotionMessageBean = bean;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,20,0));
        panel1.add(new JLabel(name));
        chckbxAutoReset = new JCheckBox("Auto Reset");
        chckbxAutoReset.addActionListener(new autoResetListener());
        btnSend = new JButton(btnActivateValue);
        btnSend.addActionListener(new buttonListener());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        itemComboBox = new JComboBox<>(items);
        itemComboBox.addActionListener(new comboboxListener());
        panel2.add(itemComboBox);
        panel2.add(btnSend);
        panel2.add(chckbxAutoReset);

        add(panel1);
        add(panel2);

    }

    private class comboboxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = itemComboBox.getSelectedItem().toString();
            if(currentItem != null && selectedItem != currentItem){
                emotionMessageBean.setValue(currentItem, 0);
            }
            currentItem = selectedItem;
            //emotionMessageBean.setValue(currentItem, val);
        }
    }

    private class autoResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox changedObj = (JCheckBox) e.getSource();
            isAutoReset = changedObj.isSelected();
            if(isAutoReset){
                btnSend.setText(btnStartValue);
            } else {
                btnSend.setText(btnActivateValue);
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


}
