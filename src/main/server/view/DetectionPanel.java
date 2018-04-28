package main.server.view;

import main.model.MessageContolBean;
import main.server.view.components.EyeControl;
import main.server.view.components.MetricControl;
import main.utils.Consts;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Detection panel, used to manipulate upperface, lowerface, eye and affective values
 * @author Ejaz Saifudeen
 * @version 1.2
 */
public class DetectionPanel extends JPanel implements Observer {

    private JTextField timeTxtField;
    MessageContolBean messageContolBean;

    public DetectionPanel(MessageContolBean messageContolBean){
        this.messageContolBean = messageContolBean;
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
        panel2.add(new MetricControl("Upperface:", Consts.upperfaceItems, messageContolBean));
        panel2.add(new MetricControl("Lowerface:", Consts.lowerfaceItems, messageContolBean));


        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
        panel3.add(new EyeControl("Eye:", Consts.eyeItems, messageContolBean));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEADING,0,10));
        panel4.add(new MetricControl("Performance Metrics:", Consts.pfMetricItems, messageContolBean));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
    }

    /**
     * Observer update, update clock tick
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        timeTxtField.setText(String.valueOf(this.messageContolBean.getClockTick()));
    }
}
