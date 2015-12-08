package robot;

import main.KeyFrame;

import java.util.ArrayList;

/**
 * Created by vasily on 08/12/15.
 */
public class RobotAnimationManager {
    private ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private int nextKeyFramesIndex;
    private double globalStartTime, localTime, savedLocalTime;

    private double xPos;
    private double zPos;
    private double rotation;
    private boolean direction;

    public RobotAnimationManager(ArrayList<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
        savedLocalTime = 0;
        localTime = getSeconds();
        globalStartTime = getSeconds();
        xPos = keyFrames.get(0).getxPosition();
        zPos = keyFrames.get(0).getzPosition();
        rotation = keyFrames.get(0).getRotation();
        direction = keyFrames.get(0).isForward();
        nextKeyFramesIndex = 1;
    }

    public void moveToNextFrame() {
        int currentKeyFrameIndex = nextKeyFramesIndex - 1;
        if(nextKeyFramesIndex > keyFrames.size()-1){
            nextKeyFramesIndex = 0;

        }
        if (currentKeyFrameIndex == -1){
            currentKeyFrameIndex = 0;
        }

        double currentSeconds = getSeconds();
        int duration = 1;
        double normalisedTime = (currentSeconds - localTime)/duration;
        xPos = LinearInterpolation(normalisedTime,
                keyFrames.get(currentKeyFrameIndex).getxPosition(),
                keyFrames.get(nextKeyFramesIndex).getxPosition());
        zPos = LinearInterpolation(normalisedTime,
                keyFrames.get(currentKeyFrameIndex).getzPosition(),
                keyFrames.get(nextKeyFramesIndex).getzPosition());
        rotation = LinearInterpolation(normalisedTime,
                keyFrames.get(currentKeyFrameIndex).getRotation(),
                keyFrames.get(nextKeyFramesIndex).getRotation());

        if (currentSeconds - localTime > duration) {
            localTime = currentSeconds;
            xPos = keyFrames.get(nextKeyFramesIndex).getxPosition();
            zPos = keyFrames.get(nextKeyFramesIndex).getzPosition();
            rotation = keyFrames.get(nextKeyFramesIndex).getRotation();
            nextKeyFramesIndex++;
        }
    }

    double LinearInterpolation(double normalisedTime, double start, double end) {
        double cosNormalisedTime = (1-Math.cos((normalisedTime*Math.PI)))/2;
        return start * (1-cosNormalisedTime) + (end*cosNormalisedTime);
//        double normalisedTimeSquare = Math.pow(normalisedTime, 2);
//        double a0 = ;
//        double a1 = ;
//        double a2 = ;
//        double a3 = ;
//
//        return a0*normalisedTime*normalisedTimeSquare+a1*normalisedTimeSquare+a2*normalisedTime+a3;
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
