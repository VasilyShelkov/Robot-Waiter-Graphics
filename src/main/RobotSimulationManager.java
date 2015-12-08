package main;

import java.util.ArrayList;

/**
 * Created by vasily on 08/12/15.
 */
public class RobotSimulationManager {
    private final ArrayList<KeyFrame> keyFrames;
    private int keyFrameIndex;
    private boolean forward;
    private double acceleration;
    private int start;
    private int end;
    private boolean decelerationPosSet;
    private double decelerationPosition;

    public RobotSimulationManager(ArrayList<KeyFrame> keyFrames) {
        this.keyFrames = keyFrames;
        this.start = 0;
        this.keyFrameIndex = 0;
//        this.end = keyFrames.get(keyFrameIndex).getEnd();

        this.forward = true;
        this.acceleration = 0;
        this.decelerationPosSet = false;
        this.decelerationPosition = calculateMinimumDecelerationDistance(start, end);
    }

    private double calculateMinimumDecelerationDistance(int start, int end) {
        return Math.abs(start - end)/2;
    }

    public void moveToNextFrame(double relPosition){
        double distanceTravelled = Math.abs(start - relPosition);
        boolean decelerating = distanceTravelled >= decelerationPosition;

        //Set up for next key frame is the current one has finished

        boolean reachedKeyframeEnd;
        if(forward) {
            reachedKeyframeEnd = relPosition <= end;
        } else {
            reachedKeyframeEnd = relPosition >= end;
        }

        if(reachedKeyframeEnd || (acceleration <= 0 && decelerating)){
            if(keyFrameIndex <= keyFrames.size()-1) {
                keyFrameIndex++;
            }
            start = (int) relPosition;
            if(keyFrameIndex == keyFrames.size()-1){
                end = (int) relPosition;
            }
//            end = keyFrames.get(keyFrameIndex).getEnd();
            setDirection(start, end);
            decelerationPosSet = false;
            decelerationPosition = calculateMinimumDecelerationDistance(start, end);
            acceleration = 0;
        }

        //When the robot is accelerating at less than 1(100%) acceleration and isn't deceleration increase acceleration
        if (acceleration < 1 && !decelerating) {
            acceleration += 0.1;
        }

        //If Past the approximate decelaration point, then start to decelerate
        if(decelerating){
            acceleration -= 0.1;
        }

//        //never go above 1 acceleration (100% acceleration)
//        if(acceleration >= 1){
//            acceleration = 1;
//        }

        //Estimate approximate distance it takes to decelerate
        if (acceleration == 1 && !decelerationPosSet){
            decelerationPosition = (Math.abs(start - end)) - distanceTravelled;
            decelerationPosSet = true;
        }
    }

    private void setDirection(int start, int end) {
        if (start > end){
            forward = true;
        } else{
            forward = false;
        }
    }

    public boolean getDirection() {
        return forward;
    }

    public double getAcceleration() {
        return acceleration;
    }
}
