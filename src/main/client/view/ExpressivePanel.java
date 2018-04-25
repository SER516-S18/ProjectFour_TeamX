package main.client.view;

import main.client.view.ExpressiveComponents.EyeComponents.LeftEye;
import main.client.view.ExpressiveComponents.EyeComponents.LeftEyeLash;
import main.client.view.ExpressiveComponents.EyeComponents.RightEye;
import main.client.view.ExpressiveComponents.EyeComponents.RightEyeLash;
import main.client.view.ExpressiveComponents.IExpressive;
import main.client.view.ExpressiveComponents.LowerFaceComponents.Clench;
import main.client.view.ExpressiveComponents.LowerFaceComponents.Mouth;
import main.client.view.ExpressiveComponents.LowerFaceComponents.Smile;
import main.client.view.ExpressiveComponents.UpperFaceComponents.LeftEyeBall;
import main.client.view.ExpressiveComponents.UpperFaceComponents.LeftEyeBrow;
import main.client.view.ExpressiveComponents.UpperFaceComponents.RightEyeBall;
import main.client.view.ExpressiveComponents.UpperFaceComponents.RightEyeBrow;
import main.model.EmotionMessageBean;
import main.model.ExpressiveBean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This panel displays the face and handles facial expressions
 *
 * @author Ejaz Saifudeen
 * @version 1.1
 */
public class ExpressivePanel extends JPanel implements Observer {

    private static final String faceLayoutPath = "faceLayout.png";
    BufferedImage img = null;
    ExpressiveBean bean = null;
    EmotionMessageBean emotionMessageBean;
    private List<IExpressive> shapes = new ArrayList<>();
    private LeftEye leftEye = new LeftEye();
    private RightEye rightEye = new RightEye();
    private LeftEyeLash leftEyeLash = new LeftEyeLash();
    private RightEyeLash rightEyeLash = new RightEyeLash();
    private LeftEyeBall leftEyeBall = new LeftEyeBall();
    private RightEyeBall rightEyeBall = new RightEyeBall();
    private LeftEyeBrow leftEyeBrow = new LeftEyeBrow();
    private RightEyeBrow rightEyeBrow = new RightEyeBrow();
    private Mouth mouth = new Mouth();
    private Smile smile = new Smile();
    private Clench clench = new Clench();

    /**
     * Constructor adds all shapes to a list and reads the facelayout image
     * to a buffered image
     */
    public ExpressivePanel(EmotionMessageBean emotionMessageBean) {

        this.emotionMessageBean = emotionMessageBean;
        shapes.add(leftEye);
        shapes.add(rightEye);
        shapes.add(leftEyeLash);
        shapes.add(rightEyeLash);
        shapes.add(leftEyeBall);
        shapes.add(rightEyeBall);
        shapes.add(leftEyeBrow);
        shapes.add(rightEyeBrow);
        shapes.add(mouth);
        shapes.add(smile);
        shapes.add(clench);

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(faceLayoutPath);
            if (inputStream != null)
                img = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addComponentListener(new ExpressiveSizeListener());
    }

    /**
     * This method is responsible for drawing all the shapes
     *
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setBackground(Color.WHITE);
        g2.drawImage(img, getRelativeX(), getRelativeY(), null);

        for (IExpressive e : shapes) {
            g2.setColor(e.getColor());
            if (e.getFill()) {
                g2.fill(e);
            } else {
                g2.setStroke(new BasicStroke(3));
                g2.draw(e);
            }
        }
    }

    /**
     * Affects facial features based on the received bean
     *
     * @param b ExpressiveBean object
     */
    private void update(ExpressiveBean b) {

        if (bean == null || !bean.equals(b)) {
            for (IExpressive e : shapes)
                e.reset(getRelativeX(), getRelativeY());

            bean = new ExpressiveBean(b);
            //Blink
            leftEye.blink(bean.isBlink());
            rightEye.blink(bean.isBlink());
            leftEyeLash.blink(bean.isBlink());
            rightEyeLash.blink(bean.isBlink());
            leftEyeBall.blink(bean.isBlink());
            rightEyeBall.blink(bean.isBlink());

            //Left wink
            leftEye.blink(bean.isLeftWink());
            leftEyeLash.blink(bean.isLeftWink());
            leftEyeBall.blink(bean.isLeftWink());

            //Right wink
            rightEye.blink(bean.isRightWink());
            rightEyeLash.blink(bean.isRightWink());
            rightEyeBall.blink(bean.isRightWink());

            //Look left
            leftEyeBall.lookLeft(bean.getLookingLeft());
            rightEyeBall.lookLeft(bean.getLookingLeft());

            //Look right
            leftEyeBall.lookRight(bean.getLookingRight());
            rightEyeBall.lookRight(bean.getLookingRight());

            //Look up
            leftEyeBall.lookUp(bean.getLookingUp());
            rightEyeBall.lookUp(bean.getLookingUp());

            //Look down
            leftEyeBall.lookDown(bean.getLookingDown());
            rightEyeBall.lookDown(bean.getLookingDown());

            //Eyebrow raise
            leftEyeBrow.raise(bean.getRaiseBrow());
            rightEyeBrow.raise(bean.getRaiseBrow());

            //Eyebrow furrow
            leftEyeBrow.furrow(bean.getFurrowBrow());
            rightEyeBrow.furrow(bean.getFurrowBrow());

            //Smile and Clench
            smile.set(bean.getSmile());
            clench.set(bean.getClench());
            mouth.set(bean.getSmile());
            mouth.set(bean.getClench());

            this.validate();
            this.repaint();
        }
    }

    /**
     * Gets relative X value such that the image will occupy the
     * center of the panel
     *
     * @return x
     */
    private int getRelativeX() {
        return (getWidth() / 2) - 156;
    }

    /**
     * Gets relative Y value such that the image will occupy the
     * center of the panel
     *
     * @return y
     */
    private int getRelativeY() {
        return (getHeight() / 2) - 156;
    }

    /**
     * update method changes facial expressions if emotion bean is
     * updated.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (this.emotionMessageBean == o) {
            update(emotionMessageBean.getExpressive());
        }
    }

    /**
     * Listens to component resize. Used to always keep the face at center of the
     * panel
     */
    private class ExpressiveSizeListener extends ComponentAdapter {

        public void componentResized(ComponentEvent ev) {
            ExpressiveBean b = null;
            if (bean != null) {
                b = new ExpressiveBean(bean);
                bean = null;
            }
            update(b);
        }
    }
}
