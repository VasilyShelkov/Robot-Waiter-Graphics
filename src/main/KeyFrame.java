package main;

/**
 * Created by vasily on 08/12/15.
 */
public class KeyFrame {
    private double xPosition;
    private double zPosition;
    private double rotation;
    private boolean direction;

    public KeyFrame(double xPosition, double zPosition, double rotation, boolean direction) {
        this.xPosition = xPosition;
        this.zPosition = zPosition;
        this.rotation = rotation;
        this.direction = direction;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getzPosition() {
        return zPosition;
    }

    public double getRotation() {
        return rotation;
    }

    public boolean isForward() {
        return direction;
    }
}
