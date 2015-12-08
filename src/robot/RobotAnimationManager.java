package robot;

import main.KeyFrame;

import java.util.ArrayList;

/**
 * Created by vasily on 08/12/15.
 */
public class RobotAnimationManager {
    private ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private int nextKeyFramesIndex;
    private final double globalStartTime, localTime, savedLocalTime;

    private double xPos;
    private double zPos;
    private double rotation;
    private boolean direction;

    public RobotAnimationManager(ArrayList<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
        localTime = 0;
        savedLocalTime = 0;
        globalStartTime = getSeconds();
        xPos = keyFrames.get(0).getxPosition();
        zPos = keyFrames.get(0).getzPosition();
        rotation = keyFrames.get(0).getRotation();
        direction = keyFrames.get(0).isForward();
        nextKeyFramesIndex = 1;
    }

    public void moveToNextFrame() {
        if(nextKeyFramesIndex > keyFrames.size()-1){
            nextKeyFramesIndex = 0;
        }
        if (getxPos() == keyFrames.get(nextKeyFramesIndex).getxPosition() &&
                getzPos() == keyFrames.get(nextKeyFramesIndex).getzPosition() &&
                getRotation() == keyFrames.get(nextKeyFramesIndex).getRotation()) {
            nextKeyFramesIndex ++;
        } else {
            xPos = keyFrames.get(nextKeyFramesIndex).getxPosition();
            zPos = keyFrames.get(nextKeyFramesIndex).getzPosition();
            rotation = keyFrames.get(nextKeyFramesIndex).getRotation();
        }
    }

    private double getSeconds() {
        return System.currentTimeMillis()/1000.0;
    }

    public double getxPos() {
        return xPos;
    }

    public double getzPos() {
        return zPos;
    }

    public double getRotation() {
        return rotation;
    }

    public boolean getDirection() {
        return direction;
    }
}
