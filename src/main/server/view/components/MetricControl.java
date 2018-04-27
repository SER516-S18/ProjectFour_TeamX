package main.server.view.components;

import main.model.EmotionMessageBean;
import main.model.MessageContolBean;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetricControl extends JPanel {

    private JComboBox<String> itemComboBox;
    private JSpinner itemSpinner;
    private String currentItem;
    private MessageContolBean messageContolBean;


    public MetricControl(String name, String[] items, MessageContolBean bean){

        messageContolBean = bean;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,20,0));
        panel1.add(new JLabel(name));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        itemComboBox = new JComboBox<>(items);
        itemComboBox.addActionListener(new comboboxListener());
        itemSpinner = new XSpinner(0,1,0.1);
        itemSpinner.addChangeListener(new spinnerListener());
        panel2.add(itemComboBox);
        panel2.add(itemSpinner);

        add(panel1);
        add(panel2);

    }

    private class comboboxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedItem = itemComboBox.getSelectedItem().toString();
            if(currentItem != null && selectedItem != currentItem){
                messageContolBean.setValue(currentItem, 0);
            }
            currentItem = selectedItem;
            double val = (double)itemSpinner.getValue();
            messageContolBean.setValue(currentItem, val);
        }
    }

    private class spinnerListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if(currentItem == null){
                currentItem = itemComboBox.getSelectedItem().toString();
            }
            double val = (double)itemSpinner.getValue();
            messageContolBean.setValue(currentItem, val);
        }
    }


}
