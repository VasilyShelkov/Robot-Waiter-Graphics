package robot;

import java.util.ArrayList;

/**
 * Created by vasily on 08/12/15.
 */
public class RobotAnimationManager {
    private ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    private int nextKeyFramesIndex;
    private double localTime, savedLocalTime;

    private double xPos;
    private double zPos;
    private double rotation;
    private boolean direction;

    private double speed;

    private double tilt;
    private double normalisedTime1;
    private boolean serving;

    public RobotAnimationManager(ArrayList<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
        savedLocalTime = 0;
        localTime = getSeconds();
        xPos = keyFrames.get(0).getxPosition();
        zPos = keyFrames.get(0).getzPosition();
        rotation = keyFrames.get(0).getRotation();
        direction = keyFrames.get(0).isForward();
        nextKeyFramesIndex = 1;
    }

    public void startAnimation() {
        localTime = getSeconds() - savedLocalTime;
    }

    public void pauseAnimation() {
        savedLocalTime = getSeconds() - localTime;
    }

    public void resetAnimation() {
        localTime = getSeconds();
        savedLocalTime = 0;
        xPos = keyFrames.get(0).getxPosition();
        zPos = keyFrames.get(0).getzPosition();
        rotation = keyFrames.get(0).getRotation();
        direction = keyFrames.get(0).isForward();
        nextKeyFramesIndex = 1;
	tilt = 0;
	speed = 0;
    }

    public void moveToNextFrame() {
        int postNextKeyFrameIndex = nextKeyFramesIndex + 1;
        if (postNextKeyFrameIndex > keyFrames.size()-1){
            postNextKeyFrameIndex = 0;
        }

        if(nextKeyFramesIndex > keyFrames.size()-1){
            nextKeyFramesIndex = 0;
        }

        int currentKeyFrameIndex = nextKeyFramesIndex - 1;
        if (currentKeyFrameIndex == -1){
            currentKeyFrameIndex = 0;
        }

        int preCurrentKeyFrameIndex = currentKeyFrameIndex - 1;
        if (preCurrentKeyFrameIndex == -1) {
            preCurrentKeyFrameIndex = 0;
        }

        double previousXPos = getxPos();
        double previousZPos = getzPos();
        double previousRotation = getRotation();

        double currentSeconds = getSeconds();
        double duration = keyFrames.get(currentKeyFrameIndex).getDuration();
        double normalisedTime = (currentSeconds - localTime)/duration;
        xPos = quadraticInterpolation(normalisedTime,
                keyFrames.get(preCurrentKeyFrameIndex).getxPosition(),
                keyFrames.get(currentKeyFrameIndex).getxPosition(),
                keyFrames.get(nextKeyFramesIndex).getxPosition(),
                keyFrames.get(postNextKeyFrameIndex).getxPosition());
        zPos = quadraticInterpolation(normalisedTime,
                keyFrames.get(preCurrentKeyFrameIndex).getzPosition(),
                keyFrames.get(currentKeyFrameIndex).getzPosition(),
                keyFrames.get(nextKeyFramesIndex).getzPosition(),
                keyFrames.get(postNextKeyFrameIndex).getzPosition());
        rotation = quadraticInterpolation(normalisedTime,
                keyFrames.get(preCurrentKeyFrameIndex).getRotation(),
                keyFrames.get(currentKeyFrameIndex).getRotation(),
                keyFrames.get(nextKeyFramesIndex).getRotation(),
                keyFrames.get(preCurrentKeyFrameIndex).getRotation());
        direction = keyFrames.get(currentKeyFrameIndex).isForward();

        double distanceTravelledSinceLastFrame = Math.sqrt(Math.pow(previousXPos - xPos, 2) + Math.pow(previousZPos-zPos, 2));
        double newSpeed = distanceTravelledSinceLastFrame*1.3;
        speed = (speed+newSpeed)/2;
        double newTilt = previousRotation - rotation;;
        tilt = (tilt*1.75 + newTilt*1.75)/2;

        if (normalisedTime > 0.5){
            normalisedTime = 1 - normalisedTime;
        }

        normalisedTime1 = normalisedTime;
        serving = keyFrames.get(currentKeyFrameIndex).isServing();

        if (currentSeconds - localTime > duration) {
            localTime = currentSeconds;
            xPos = keyFrames.get(nextKeyFramesIndex).getxPosition();
            zPos = keyFrames.get(nextKeyFramesIndex).getzPosition();
            rotation = keyFrames.get(nextKeyFramesIndex).getRotation();
            nextKeyFramesIndex++;
        }
    }

    double quadraticInterpolation(double normalisedTime, double preStart, double start, double end, double postEnd) {
        double normalisedTimeSquare = Math.pow(normalisedTime, 2);
        double a0 = postEnd - end - preStart + start;
        double a1 = preStart - start - a0;
        double a2 = end - preStart;
        double a3 = start;

        return a0*normalisedTime*normalisedTimeSquare+a1*normalisedTimeSquare+a2*normalisedTime+a3;
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

    public double getSpeed() {
        return speed;
    }

    public double getTilt() {
        return tilt;
    }

    public double getNormalisedTime1() {
        return normalisedTime1;
    }

    public boolean isServing() {
        return serving;
    }
}
