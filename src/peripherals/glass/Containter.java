package peripherals.glass;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;

/**
 * Created by vasily on 04/12/15.
 */
public class Containter extends SceneGraph {
    private final double containerRadius;
    private final double containerHeight;
    private final int slices;

    public Containter(double handleHeight, double containerBottomRadius, int slices) {
        super();

        this.containerRadius = containerBottomRadius;
        this.containerHeight = handleHeight*1.5;
        this.slices = slices;
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCylinder(containerRadius, containerHeight, slices, 1);
    }
}
