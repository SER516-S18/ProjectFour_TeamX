package main.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Panel to display color for the graph plot representing effects in form of boxes. And display the time difference
 * between data received for the graph points.
 *
 * @author Shaunak Shah
 * @author Ayan Shah
 * @version 1.0
 */
public class MetricsValuePanel extends JPanel {
    private ColorBox interest;
    private ColorBox engagement;
    private ColorBox stress;
    private ColorBox relaxation;
    private ColorBox excitement;
    private ColorBox focus;
    private JFormattedTextField displayLength;
    private JButton setLength;
    private MetricGraphPanel metricGraphPanel;

    /**
     * Constructor to initialize message bean and chart varibales
     *
     * @param metricGraphPanel1 MetricGraphPanel object to instantiate
     *                          class object for graphPanel control
     */
    public MetricsValuePanel(MetricGraphPanel metricGraphPanel1) {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.metricGraphPanel = metricGraphPanel1;
        interest = new ColorBox();
        interest.setBoxColor(0);
        interest.setEmotionName("Interest");
        interest.setBounds(35, 15, 150, 150);
        interest.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            interest.setBackground(getInterstColor());
                            metricGraphPanel.updateColor(0, getInterstColor());
                        }
                    }
                }
        );

        this.add(interest);

        engagement = new ColorBox();
        engagement.setEmotionName("Engagement");
        engagement.setBoxColor(1);
        engagement.setBounds(190, 15, 150, 150);
        engagement.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            engagement.setBackground(getEngagementColor());
                            metricGraphPanel.updateColor(1, getEngagementColor());
                        }
                    }
                }
        );
        this.add(engagement);

        stress = new ColorBox();
        stress.setEmotionName("Stress");
        stress.setBoxColor(2);
        stress.setBounds(35, 170, 150, 150);
        stress.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            stress.setBackground(getStressColor());
                            metricGraphPanel.updateColor(2, getStressColor());
                        }
                    }
                }
        );
        this.add(stress);

        relaxation = new ColorBox();
        relaxation.setEmotionName("Relaxation");
        relaxation.setBoxColor(3);
        relaxation.setBounds(190, 170, 150, 150);
        relaxation.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            relaxation.setBackground(getRelaxationColor());
                            metricGraphPanel.updateColor(3, getRelaxationColor());
                        }
                    }
                }
        );
        this.add(relaxation);

        excitement = new ColorBox();
        excitement.setEmotionName("Excitement");
        excitement.setBoxColor(4);
        excitement.setBounds(35, 325, 150, 150);
        excitement.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            excitement.setBackground(getExcitemetColor());
                            metricGraphPanel.updateColor(4, getExcitemetColor());
                        }
                    }
                }
        );
        this.add(excitement);


        focus = new ColorBox();
        focus.setEmotionName("Focus");
        focus.setBoxColor(5);
        focus.setBounds(190, 325, 150, 150);
        focus.getDropdown().addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            focus.setBackground(getFocusColor());
                            metricGraphPanel.updateColor(5, getFocusColor());
                        }
                    }
                }
        );
        this.add(focus);

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
     * private method to return the color selected by user for interest plot.
     */
    private Color getInterstColor() {
        return interest.getBoxColor();
    }

    /**
     * private method to return the color selected by user for engagement plot.
     */
    private Color getEngagementColor() {
        return engagement.getBoxColor();
    }

    /**
     * private method to return the color selected by user for stress plot.
     */
    private Color getStressColor() {
        return stress.getBoxColor();
    }

    /**
     * private method to return the color selected by user for relaxation plot.
     */
    private Color getRelaxationColor() {
        return relaxation.getBoxColor();
    }

    /**
     * private method to return the color selected by user for excitement plot.
     */
    private Color getExcitemetColor() {
        return excitement.getBoxColor();
    }

    /**
     * private method to return the color selected by user for focus plot.
     */
    private Color getFocusColor() {
        return focus.getBoxColor();
    }

    /**
     * private method to get the display length as per data entered by the user. Default is set to 1.
     */
    private double getDisplayLength() {
        return (double) displayLength.getValue();
    }
}
