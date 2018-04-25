package main.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel box to display an effect so that user can select a color for it. Also see the updated color in form of changed
 * box color. Panel (this class) is used by Metrics Value Panel (class) to create multiple effect boxes.
 *
 * @author Shaunak Shah
 * @version 1.0
 */
public class ColorBox extends JPanel {
    private JComboBox<String> dropdown;
    private JLabel emotionName;
    private String[] colorList = new String[]{"Red", "Green", "Yellow", "Blue", "Pink", "Orange"};
    private Color colors[] = new Color[]{Color.RED.darker(), Color.GREEN.darker(), Color.YELLOW.darker(),
            Color.BLUE.darker(), Color.PINK.darker(), Color.ORANGE.darker()};

    public ColorBox() {
        setSize(150, 150);
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = 0;
        grid.gridy = 0;

        dropdown = new JComboBox<>(colorList);
        dropdown.setBorder(BorderFactory.createEmptyBorder());
        dropdown.setBackground(Color.GRAY);
        this.add(dropdown, grid);

        emotionName = new JLabel();
        emotionName.setPreferredSize(new Dimension(200, 0));
        emotionName.setText("default");
        grid.weighty = 0.001;
        grid.gridy = 1;
        this.add(emotionName, grid);
    }

    /**
     * Public method to set the title of the effect that the box (this class panel) will represent.
     */
    public void setEmotionName(String emotion) {
        emotionName.setText(emotion);
    }

    /**
     * Public method that returns the color selected by the user. So that changes can be made to the graph accordingly.
     */
    public Color getBoxColor() {
        return colors[dropdown.getSelectedIndex()];
    }

    /**
     * Public method to initialize the color of the box when the effect is created, so that effect can have its own
     * color.
     */
    public void setBoxColor(int index) {
        dropdown.setSelectedIndex(index);
        setBackground(colors[index]);
    }

    public JComboBox<String> getDropdown() {
        return dropdown;
    }
}
