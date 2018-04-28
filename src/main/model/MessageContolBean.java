package main.model;

import java.util.Observable;

public class MessageContolBean extends Observable{

    private EmotionMessageBean emotionMessageBean;

    public boolean isServerStarted() {
        return isServerStarted;
    }

    public void setServerStarted(boolean serverStarted) {
        isServerStarted = serverStarted;
    }

    public boolean isEyeAutoReset() {
        return isEyeAutoReset;
    }

    public void setEyeAutoReset(boolean eyeAutoReset) {
        isEyeAutoReset = eyeAutoReset;
    }

    private boolean isServerAutoReset = false;

    private boolean isServerStarted = false;

    private boolean isEyeAutoReset = false;

    public boolean isServerAutoReset() {
        return isServerAutoReset;
    }

    public void setServerAutoReset(boolean serverAutoReset) {
        isServerAutoReset = serverAutoReset;
    }

    public boolean isEyeActivated() {
        return isEyeActivated;
    }

    public void setEyeActivated(boolean eyeActivated) {
        isEyeActivated = eyeActivated;
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    private boolean isEyeActivated = false;

    private double interval;


    public String getCurrentEyeItem() {
        return currentEyeItem;
    }

    public void setCurrentEyeItem(String currentEyeItem) {
        this.currentEyeItem = currentEyeItem;
    }

    private String currentEyeItem;

    public double getClockTick() {
        return clockTick;
    }

    public void setClockTick(double clockTick) {
        this.clockTick = clockTick;
        setChanged();
        this.notifyObservers();
    }

    private double clockTick;



    public EmotionMessageBean getEmotionMessageBean() {
        return emotionMessageBean;
    }

    public void setEmotionMessageBean(EmotionMessageBean bean){
        emotionMessageBean = bean;
    }

    public boolean getEyeVal(String item){
        switch(item){
            case "Blink":{
                return emotionMessageBean.getExpressive().isBlink();
            }
            case "Wink Left":{
                return emotionMessageBean.getExpressive().isLeftWink();
            }
            case "Wink Right":{
                return emotionMessageBean.getExpressive().isRightWink();
            }
        }
        return false;
    }


    public void setValue(String item, double value){
        switch (item){
            case "Raise Brow":{
                emotionMessageBean.getExpressive().setRaiseBrow(value);
                break;
            }
            case "Furrow Brow":{
                emotionMessageBean.getExpressive().setRaiseBrow(value);
                break;
            }
            case "Look Left":{
                emotionMessageBean.getExpressive().setLookingLeft(value);
                break;
            }
            case "Look Right":{
                emotionMessageBean.getExpressive().setLookingRight(value);
                break;
            }
            case "Look Up":{
                emotionMessageBean.getExpressive().setLookingUp(value);
                break;
            }
            case "Look Down":{
                emotionMessageBean.getExpressive().setLookingDown(value);
                break;
            }
            case "Smile":{
                emotionMessageBean.getExpressive().setSmile(value);
                break;
            }
            case "Clench":{
                emotionMessageBean.getExpressive().setClench(value);
                break;
            }
            case "Blink":{
                emotionMessageBean.getExpressive().setBlink(value == 1? true : false);
                break;
            }
            case "Wink Left":{
                emotionMessageBean.getExpressive().setLeftWink(value == 1? true : false);
                break;
            }
            case "Wink Right":{
                emotionMessageBean.getExpressive().setRightWink(value == 1? true : false);
                break;
            }
            case "Interest":{
                emotionMessageBean.getAffective().setInterest(value);
                break;
            }
            case "Engagement":{
                emotionMessageBean.getAffective().setEngagement(value);
                break;
            }
            case "Stress":{
                emotionMessageBean.getAffective().setStress(value);
                break;
            }
            case "Excitement":{
                emotionMessageBean.getAffective().setExcitement(value);
                break;
            }
            case "Relaxation":{
                emotionMessageBean.getAffective().setRelaxation(value);
                break;
            }
            case "Focus":{
                emotionMessageBean.getAffective().setFocus(value);
                break;
            }
        }
    }

}
