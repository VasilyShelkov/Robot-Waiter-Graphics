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
    private boolean serving;

    public KeyFrame(double xPosition, double zPosition, double rotation, boolean direction, double duration) {
        this.xPosition = xPosition;
        this.zPosition = zPosition;
        this.rotation = rotation;
        this.direction = direction;
        this.duration = duration;
        this.serving = false;
    }

    public KeyFrame(double xPosition, double zPosition, double rotation, boolean direction,double duration, boolean serving) {
        this.xPosition = xPosition;
        this.zPosition = zPosition;
        this.rotation = rotation;
        this.direction = direction;
        this.duration = duration;
        this.serving = serving;
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

    public boolean isServing() {
        return serving;
    }
}
