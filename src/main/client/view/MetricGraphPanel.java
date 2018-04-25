package main.client.view;

import main.model.AffectiveBean;
import main.model.EmotionMessageBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Graph Panel to display chart in graph tab
 *
 * @author Shaunak Shah
 * @author Ayan Shah
 * @version 1.0
 */

public class MetricGraphPanel extends JPanel implements Observer {

    final private int channelNumber = 6;
    private TimeSeries[] metricValues;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private int frequency = 1;
    private Color colorList[] = new Color[]{Color.RED.darker(), Color.GREEN.darker(), Color.YELLOW.darker(),
            Color.BLUE.darker(), Color.PINK.darker(), Color.ORANGE.darker()};
    private EmotionMessageBean emotionMessageBean;
    private XYPlot plot;
    private long start;
    private XYLineAndShapeRenderer renderer;

    /**
     * Constructor to initialize message bean and chart varibales
     *
     * @param EmotionMessageBean emotionMessageBean to read data
     */
    public MetricGraphPanel(EmotionMessageBean emotionMessageBean) {
        XYDataset dataSet = createDataSet(channelNumber);
        chart = createChart(dataSet);
        chartPanel = new ChartPanel(chart);
        chartPanel.addComponentListener(new ChartSizeListener());
        chartPanel.setMouseZoomable(true, true);
        chartPanel.setPreferredSize(new Dimension(520, 520));
        this.emotionMessageBean = emotionMessageBean;
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Public method to continuously update the values for the channels in graph
     *
     * @param AffectiveBean affective message data bean to extract affective data values
     */
    public void updateMetric(AffectiveBean bean) {
        long current = (System.currentTimeMillis() - start);
        Date date = new Date(current / 1000);
        Millisecond millisecond = new Millisecond(date);
        metricValues[0].addOrUpdate(millisecond, bean.getInterest());
        metricValues[1].addOrUpdate(millisecond, bean.getEngagement());
        metricValues[2].addOrUpdate(millisecond, bean.getStress());
        metricValues[3].addOrUpdate(millisecond, bean.getRelaxation());
        metricValues[4].addOrUpdate(millisecond, bean.getExcitement());
        metricValues[5].addOrUpdate(millisecond, bean.getFocus());
    }

    /**
     * Public method used to update the number of channels in a graph. Creates new chart panel.
     * And it to the panel.
     *
     * @param length double lenght varible to set domain range
     */
    public void updateDisplayLength(double length) {
        plot = chart.getXYPlot();
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0.00, length);
        chartPanel.repaint();
    }

    /**
     * Public method to change the color of a specific channel.
     *
     * @param key   integer parameter key to identify emotion series
     * @param color Color parameter to set selected color for the series
     */
    public void updateColor(int key, Color color) {
        plot = chart.getXYPlot();
        renderer.setSeriesPaint(key, color);
        plot.setRenderer(renderer);

    }

    /**
     * Private method to create data chart based on the data set provided. Used when panel is initialized or channel
     * count is changed.
     *
     * @param dataSet XYDataSet parameter to set graph panel
     */
    private JFreeChart createChart(XYDataset dataSet) {
        JFreeChart chart = ChartFactory.createXYLineChart("Performance Metric", "Time",
                "Amount", dataSet,
                PlotOrientation.VERTICAL, true, false, false);
        chart.removeLegend();
        plot = chart.getXYPlot();
        renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < dataSet.getSeriesCount(); i++) {
            renderer.setSeriesPaint(i, colorList[i]);
        }

        start = System.currentTimeMillis();
        ValueAxis domain = plot.getDomainAxis();
        domain.setRange(0, 300);
        domain.setVerticalTickLabels(false);
        domain.setAutoTickUnitSelection(true);

        ValueAxis range = plot.getRangeAxis();
        range.setRange(0.00, 1.00);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);
        return chart;
    }

    /**
     * Private method to initialize new data set based on the number of channels provided. Used when channel number is
     * addded or updated by the method 'update channel number'.
     *
     * @param channelNumber int parameter to set channels for each emotion in the chart
     */
    private XYDataset createDataSet(int channelNumber) {
        final TimeSeriesCollection dataSet = new TimeSeriesCollection();
        metricValues = new TimeSeries[channelNumber];
        for (int i = 0; i < channelNumber; i++) {
            metricValues[i] = new TimeSeries("Channel " + (i + 1));
            dataSet.addSeries(metricValues[i]);
        }
        return dataSet;
    }

    /**
     * Private method to set maximum amount of time each plot will be persist in the graph
     *
     * @param maxPlots integer paramter to set maximum age for each series
     */
    private void setMaximumPlots(int maxPlots) {

        int maxItemAge = (1000 / frequency) * maxPlots;

        for (int i = 0; i < channelNumber; i++) {
            metricValues[i].setMaximumItemAge(maxItemAge);
        }
    }

    /**
     * Public update method inherited from observer to update series data
     * when the observer is notified with new data
     *
     * @param Observable o message bean parameter sent by the observer on update
     */
    @Override
    public void update(Observable o, Object arg) {
        if (this.emotionMessageBean == o) {
            updateMetric(emotionMessageBean.getAffective());
        }
    }

    private class ChartSizeListener extends ComponentAdapter {

        public void componentResized(ComponentEvent ev) {

            int maxPlots = chartPanel.getWidth() / 15;
            setMaximumPlots(maxPlots);
        }
    }
}
