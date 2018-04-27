package main.server.view.components;

import javax.swing.*;
import java.awt.*;

public class MetricControl extends JPanel {

    private JComboBox<String> itemComboBox;
    private JSpinner itemSpinner;

    public MetricControl(String name, String[] items){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,20,0));
        panel1.add(new JLabel(name));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        itemComboBox = new JComboBox<>(items);
        itemSpinner = new XSpinner(0,1,0.1);
        panel2.add(itemComboBox);
        panel2.add(itemSpinner);

        add(panel1);
        add(panel2);

    }
}
