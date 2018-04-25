package main.client.view.ExpressiveComponents;

import java.awt.*;

/**
 * Interface for all facial features
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public interface IExpressive extends Shape {

    /**
     * Resets shape to original shape
     */
    void reset(int x, int y);

    /**
     * Returns if the shape needs to be filled with color or not
     *
     * @return boolean
     */
    boolean getFill();

    /**
     * Returns the color for the shape
     *
     * @return Color
     */
    Color getColor();
}
