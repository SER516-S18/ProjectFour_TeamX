package main.server.view;

import com.sun.deploy.panel.JavaPanel;
import main.server.view.components.MetricControl;
import main.utils.Consts;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DetectionPanel extends JPanel {

    private JTextField timeTxtField;
    private JComboBox<String> upperfaceComboBox;
    private JSpinner upperfaceSpinner;
    private JComboBox<String> lowerfaceComboBox;
    private JSpinner lowerfaceSpinner;
    private JComboBox<String> eyeComboBox;
    private JCheckBox chckbxReset;
    private JButton btnActivate;
    private JComboBox<String> performanceMetricsComboBox;
    private JSpinner pfMetricSpinner;
    private String upperfaceSelectedItem;
    private double upperfaceSelectedValue;
    private String lowerfaceSelectedItem;
    private double lowerfaceSelectedValue;
    private String eyeStateSelectedItem;
    private boolean isResetChecked;
    private boolean isActivated;
    private String pfMetricSelectedItem;
    private double pfMetricSelectedValue;
    private double timeTxtFieldVal;

    public DetectionPanel(){
        this.setBorder(new TitledBorder("Detection"));

        timeTxtField = new JTextField();
        timeTxtField.setText("0.00");
        timeTxtField.setEnabled(false);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
        panel1.add(new JLabel("<html>Time:</html>"));
        panel1.add(timeTxtField);
        panel1.add(new JLabel("<html>Seconds</html>"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
        panel2.add(new MetricControl("Upperface:", Consts.upperfaceItems));
        panel2.add(new MetricControl("Lowerface:", Consts.lowerfaceItems));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
        panel3.add(new MetricControl("Eye:", Consts.eyeItems));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
        panel4.add(new MetricControl("Performance Metrics:", Consts.pfMetricItems));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
    }
}
