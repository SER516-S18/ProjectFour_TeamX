package main.model;

/**
 * Bean which stores the emotion message to be sent to client.
 *
 * @author Balachandar Sampath
 * @version 1.0
 */
public class ExpressiveBean {


    private boolean blink;
    private boolean rightWink;
    private boolean leftWink;

    private double lookingRight;
    private double lookingLeft;
    private double eyeBrowRaise;
    private double eyeBrowFurrow;
    private double smile;
    private double clench;
    private double lookingUp;
    private double lookingDown;

    public ExpressiveBean() {
    }

    /**
     * Copy constructor
     *
     * @param bean
     */
    public ExpressiveBean(ExpressiveBean bean) {
        if (bean == null)
            return;

        lookingRight = bean.lookingRight;
        blink = bean.blink;
        rightWink = bean.rightWink;
        leftWink = bean.leftWink;
        lookingLeft = bean.lookingLeft;
        eyeBrowRaise = bean.eyeBrowRaise;
        eyeBrowFurrow = bean.eyeBrowFurrow;
        smile = bean.smile;
        clench = bean.clench;
        lookingUp = bean.lookingUp;
        lookingDown = bean.lookingDown;
    }

    /**
     * Setters/Getters for class properties
     */
    public boolean isBlink() {
        return blink;
    }

    /**
     * @param blink
     */
    public void setBlink(boolean blink) {
        this.blink = blink;
    }

    public boolean isRightWink() {
        return rightWink;
    }

    /**
     * @param rightWink
     */
    public void setRightWink(boolean rightWink) {

        this.rightWink = rightWink;
    }

    public boolean isLeftWink() {
        return leftWink;
    }

    /**
     * @param leftWink
     */
    public void setLeftWink(boolean leftWink) {

        this.leftWink = leftWink;
    }

    public double getLookingLeft() {
        return lookingLeft;
    }

    /**
     * @param lookingLeft
     */
    public void setLookingLeft(double lookingLeft) {

        this.lookingLeft = lookingLeft;
    }

    public double getLookingRight() {
        return lookingRight;
    }

    /**
     * @param lookingRight
     */
    public void setLookingRight(double lookingRight) {

        this.lookingRight = lookingRight;
    }

    public double getRaiseBrow() {
        return eyeBrowRaise;
    }

    /**
     * @param raiseBrow
     */
    public void setRaiseBrow(double raiseBrow) {

        this.eyeBrowRaise = raiseBrow;
    }

    public double getFurrowBrow() {
        return eyeBrowFurrow;
    }

    /**
     * @param eyeBrowFurrow
     */
    public void setFurrowBrow(double eyeBrowFurrow) {

        this.eyeBrowFurrow = eyeBrowFurrow;
    }

    public double getSmile() {
        return smile;
    }

    /**
     * @param smile
     */
    public void setSmile(double smile) {
        this.smile = smile;
    }

    public double getClench() {
        return clench;
    }

    /**
     * @param clench
     */
    public void setClench(double clench) {
        this.clench = clench;
    }

    public double getLookingUp() {
        return lookingUp;
    }

    /**
     * @param lookingUp
     */
    public void setLookingUp(double lookingUp) {

        this.lookingUp = lookingUp;
    }

    public double getLookingDown() {
        return lookingDown;
    }

    /**
     * @param lookingDown
     */
    public void setLookingDown(double lookingDown) {

        this.lookingDown = lookingDown;
    }

    @Override
    public String toString() {
        return "ExpressiveBean{" +
                ", blink=" + blink +
                ", rightWink=" + rightWink +
                ", leftWink=" + leftWink +
                "lookingRight=" + lookingRight +
                ", lookingLeft=" + lookingLeft +
                ", eyeBrowRaise=" + eyeBrowRaise +
                ", eyeBrowFurrow=" + eyeBrowFurrow +
                ", smile=" + smile +
                ", clench=" + clench +
                ", lookingUp=" + lookingUp +
                ", lookingDown=" + lookingDown +
                '}';
    }

    /**
     * Checks if the parameter bean is equal to the Expressive bean
     *
     * @param bean
     * @return boolean value based on comparison
     */
    public boolean equals(ExpressiveBean bean) {

        return lookingRight == bean.lookingRight &&
                blink == bean.blink &&
                rightWink == bean.rightWink &&
                leftWink == bean.leftWink &&
                lookingLeft == bean.lookingLeft &&
                eyeBrowRaise == bean.eyeBrowRaise &&
                eyeBrowFurrow == bean.eyeBrowFurrow &&
                smile == bean.smile &&
                clench == bean.clench &&
                lookingUp == bean.lookingUp &&
                lookingDown == bean.lookingDown;
    }
}
