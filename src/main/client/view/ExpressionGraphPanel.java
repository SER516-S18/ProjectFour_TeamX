package main.client.view;

import main.model.EmotionMessageBean;
import main.model.ExpressiveBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This panel displays the graph for different facial expressions
 *
 * @author Nishiti Sawant
 * @version 1.1
 */

public class ExpressionGraphPanel extends JPanel implements Observer {

    private EmotionMessageBean emotionMessageBean;
    private TimeSeries blinkValue = new TimeSeries("Blink");
    private TimeSeries rightWinkValue = new TimeSeries("Right Wink");
    private TimeSeries leftWinkValue = new TimeSeries("Left Wink");
    private TimeSeries lookingRightValue = new TimeSeries("Looking Right");
    private TimeSeries lookingLeftValue = new TimeSeries("Looking Left");
    private TimeSeries eyeBrowRaiseValue = new TimeSeries("Eyebrow Raise");
    private TimeSeries eyeBrowFurrowValue = new TimeSeries("Eyebrow Furrow ");
    private TimeSeries smileValue = new TimeSeries("Smile");
    private TimeSeries clenchValue = new TimeSeries("Clench");
    private TimeSeries lookingUpValue = new TimeSeries("Looking Up");
    private TimeSeries lookingDownValue = new TimeSeries("Looking Down");

    /**
     * constructor creates subpanels to display multiple charts and labels
     *
     * @param emotionMessageBean Emotion Message Bean object
     */
    public ExpressionGraphPanel(EmotionMessageBean emotionMessageBean) {
        this.emotionMessageBean = emotionMessageBean;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.setBackground(Color.WHITE);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.add(subPanel, BorderLayout.CENTER);
        subPanel.setPreferredSize(new Dimension(700, 550));
        subPanel.setBackground(Color.WHITE);
        for (int i = 0; i < 11; i++) {
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weighty = 1;
            constraints.weightx = 1;
            subPanel.add(createChartPanel(i), constraints);

            constraints.ipadx = 1;
            constraints.ipady = 0;
            constraints.gridx = 1;
            constraints.gridy = i;
            constraints.weighty = 0;
            constraints.weightx = 0;
            JLabel label = new JLabel(createLabel(i));
            subPanel.add(label, constraints);
        }

    }

    /**
     * creates a chart panel holder and invokes create chart function
     *
     * @param type expression type
     * @return chart panel
     */
    private JPanel createChartPanel(int type) {
        JPanel chartPanelHolder = new JPanel();
        chartPanelHolder.setPreferredSize(new Dimension(600, 45));
        chartPanelHolder.setLayout(new BorderLayout());
        XYDataset dataSet = createDataSet(type);
        JFreeChart graph = createChart(dataSet);
        ChartPanel chart = new ChartPanel(graph);
        chart.setMouseZoomable(true, true);
        chartPanelHolder.add(chart, BorderLayout.CENTER);
        return chartPanelHolder;
    }

    /**
     * creates a dataset for an expression depending in the expression type passsed
     *
     * @param type expression type
     * @return XYDataset
     */
    private XYDataset createDataSet(int type) {
        switch (type) {
            case 0:
                return new TimeSeriesCollection(blinkValue);
            case 1:
                return new TimeSeriesCollection(rightWinkValue);
            case 2:
                return new TimeSeriesCollection(leftWinkValue);
            case 3:
                return new TimeSeriesCollection(lookingRightValue);
            case 4:
                return new TimeSeriesCollection(lookingLeftValue);
            case 5:
                return new TimeSeriesCollection(eyeBrowRaiseValue);
            case 6:
                return new TimeSeriesCollection(eyeBrowFurrowValue);
            case 7:
                return new TimeSeriesCollection(smileValue);
            case 8:
                return new TimeSeriesCollection(clenchValue);
            case 9:
                return new TimeSeriesCollection(lookingUpValue);
            case 10:
                return new TimeSeriesCollection(lookingDownValue);
        }

        return new TimeSeriesCollection();
    }

    /**
     * creates charts from the dataset
     *
     * @param dataSet dataset
     * @return Chart
     */
    private JFreeChart createChart(final XYDataset dataSet) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "",
                "Seconds",
                "Value",
                dataSet,
                false,
                false,
                false);
        XYPlot plot = chart.getXYPlot();
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        XYItemRenderer r = plot.getRenderer();
        BasicStroke wideLine = new BasicStroke(2.0f);
        r.setSeriesStroke(0, wideLine);

        org.jfree.chart.axis.ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setAutoRange(true);

        xAxis.setFixedAutoRange(60000.0);
        xAxis.setVerticalTickLabels(false);

        xAxis.setAutoTickUnitSelection(true);
        xAxis.setVisible(false);

        org.jfree.chart.axis.ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0.0, 1.1);
        yAxis.setVisible(false);

        return chart;
    }

    /**
     * creates labels to display the expression type
     *
     * @param type expression type
     * @return expression name
     */
    private String createLabel(int type) {
        String expression = "";
        switch (type) {
            case 0:
                expression = "Blink";
                break;
            case 1:
                expression = "Right Wink";
                break;
            case 2:
                expression = "Left wink";
                break;
            case 3:
                expression = "Looking Right";
                break;
            case 4:
                expression = "Looking Left";
                break;
            case 5:
                expression = "Eye Brow Raise";
                break;
            case 6:
                expression = "Eye Brow Furrow";
                break;
            case 7:
                expression = "Smile";
                break;
            case 8:
                expression = "Clench";
                break;
            case 9:
                expression = "Looking Up";
                break;
            case 10:
                expression = "Looking Down";
                break;
        }
        return expression;
    }

    /**
     * updates the dataset used for graph depending on the received bean
     *
     * @param expressiveBean bean object
     */
    private void updateValue(ExpressiveBean expressiveBean) {
        Double val;
        final Millisecond current = new Millisecond();
        if (expressiveBean.isBlink())
            val = 1.00;
        else
            val = 0.00;
        blinkValue.add(current, val);
        if (expressiveBean.isRightWink())
            val = 1.00;
        else
            val = 0.00;
        rightWinkValue.add(current, val);
        if (expressiveBean.isLeftWink())
            val = 1.00;
        else
            val = 0.00;
        leftWinkValue.add(current, val);
        lookingRightValue.add(current, expressiveBean.getLookingRight());
        lookingLeftValue.add(current, expressiveBean.getLookingLeft());
        eyeBrowRaiseValue.add(current, expressiveBean.getRaiseBrow());
        eyeBrowFurrowValue.add(current, expressiveBean.getFurrowBrow());
        smileValue.add(current, expressiveBean.getSmile());
        clenchValue.add(current, expressiveBean.getClench());
        lookingUpValue.add(current, expressiveBean.getLookingUp());
        lookingDownValue.add(current, expressiveBean.getLookingDown());
    }

    /**
     * passes the updated value of the bean object to other methods
     *
     * @param o   observable
     * @param arg object
     */
    @Override
    public void update(Observable o, Object arg) {
        if (this.emotionMessageBean == o) {
            updateValue(emotionMessageBean.getExpressive());
        }
    }
}
