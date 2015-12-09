package Primitives;

/**
 * Created by vasily on 03/12/15.
 */
public class Vertex {

    private double x;
    private double y;
    private double z;
    private double u;
    private double v;

    public Vertex(double x, double y, double z, double u, double v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }
}
