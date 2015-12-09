package robot;

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

        double currentSeconds = getSeconds();
        int duration = 1;
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
}
