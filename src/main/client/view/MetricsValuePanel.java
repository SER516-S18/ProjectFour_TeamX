package main.client.view;

import main.server.view.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventListener;

/**
 * Panel to display color for the graph plot representing effects in form of boxes. And display the time difference
 * between data received for the graph points.
 *
 * @author Shaunak Shah
 * @author Ayan Shah
 * @version 1.0
 */
public class MetricsValuePanel extends JPanel {
    private JFormattedTextField displayLength;
    private JButton setLength;
    private MetricGraphPanel metricGraphPanel;
    ColorBox[] colorBoxes = new ColorBox[6];

    /**
     * Constructor to initialize message bean and chart varibales
     *
     * @param metricGraphPanel1 MetricGraphPanel object to instantiate
     *                          class object for graphPanel control
     */
    public MetricsValuePanel(MetricGraphPanel metricGraphPanel1) {
        this.setLayout(new GridLayout(3,2));
        this.setBackground(Color.LIGHT_GRAY);
        this.metricGraphPanel = metricGraphPanel1;
        colorBoxes[0] = new ColorBox(0);
        colorBoxes[0].setBoxColor(0);
        colorBoxes[0].setEmotionName("Interest");
        colorBoxes[0].getDropdown().addItemListener(new colorBoxListner());

        this.add(colorBoxes[0]);

        colorBoxes[1] = new ColorBox(1);
        colorBoxes[1].setEmotionName("Engagement");
        colorBoxes[1].setBoxColor(1);
        colorBoxes[1].getDropdown().addItemListener(new colorBoxListner());
        this.add(colorBoxes[1]);

        colorBoxes[2] = new ColorBox(2);
        colorBoxes[2] .setEmotionName("Stress");
        colorBoxes[2] .setBoxColor(2);
        colorBoxes[2] .getDropdown().addItemListener(new colorBoxListner());
        this.add(colorBoxes[2] );

        colorBoxes[3] = new ColorBox(3);
        colorBoxes[3].setEmotionName("Relaxation");
        colorBoxes[3].setBoxColor(3);
        colorBoxes[3].getDropdown().addItemListener(new colorBoxListner());
        this.add(colorBoxes[3]);

        colorBoxes[4] = new ColorBox(4);
        colorBoxes[4].setEmotionName("Excitement");
        colorBoxes[4].setBoxColor(4);
        colorBoxes[4].getDropdown().addItemListener(new colorBoxListner());
        this.add(colorBoxes[4]);


        colorBoxes[5] = new ColorBox(5);
        colorBoxes[5].setEmotionName("Focus");
        colorBoxes[5].setBoxColor(5);
        colorBoxes[5].getDropdown().addItemListener(new colorBoxListner());
        this.add(colorBoxes[5]);

        JLabel title = new JLabel();
        title.setText("Display Length:");
        title.setBounds(50, 495, 140, 30);
        this.add(title);

        displayLength = new JFormattedTextField();
        displayLength.setValue(new Double(300));
        displayLength.setOpaque(true);
        displayLength.setBackground(Color.GRAY);
        displayLength.setBounds(145, 495, 50, 30);
        this.add(displayLength);

        setLength = new JButton();
        setLength.setText("Seconds");
        setLength.setOpaque(true);
        setLength.setBackground(Color.GRAY);
        setLength.setBounds(210, 495, 100, 30);
        setLength.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                metricGraphPanel.updateDisplayLength(getDisplayLength());
            }
        });
        this.add(setLength);
    }

    /**
     * private method to get the display length as per data entered by the user. Default is set to 1.
     */
    private double getDisplayLength() {
        return (double) displayLength.getValue();
    }


    private class colorBoxListner implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
                int key = Integer.parseInt(comboBox.getName());
                colorBoxes[key].setBackground(colorBoxes[key].getBoxColor());
                metricGraphPanel.updateColor(key, colorBoxes[key].getBoxColor());
            }
        }
    }
}
