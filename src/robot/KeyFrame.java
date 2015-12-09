package robot;

/**
 * Created by vasily on 08/12/15.
 */
public class KeyFrame {
    private double xPosition;
    private double zPosition;
    private double rotation;
    private boolean direction;
    private final double duration;

    public KeyFrame(double xPosition, double zPosition, double rotation, boolean direction, double duration) {
        this.xPosition = xPosition;
        this.zPosition = zPosition;
        this.rotation = rotation;
        this.direction = direction;
        this.duration = duration;
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

    public double getDuration() {
        return duration;
    }
}
