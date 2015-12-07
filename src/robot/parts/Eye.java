package robot.parts;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;
import scene.styling.Obsidian;

/**
 * Created by vasily on 04/12/15.
 */
public class Eye extends SceneGraph{
    private double eyeRadius;
    private int slices;

    public Eye(double x, double y, double z, double eyeRadius, int slices) {
        super(x,y,z, new Obsidian());
        this.eyeRadius = eyeRadius;
        this.slices = slices;
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidSphere(eyeRadius, slices, slices);
    }
}
