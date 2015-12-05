package peripherals.glass;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;

/**
 * Created by vasily on 04/12/15.
 */
public class ContainerBottom extends SceneGraph {
    private final double containerBottomRadius;
    private final int slices;

    public ContainerBottom(double handleHeight, double handleRadius, int slices) {
        super(0,0,handleHeight);

        this.containerBottomRadius = handleRadius*3.5;
        this.slices = slices;

        addChild(new Containter(handleHeight, containerBottomRadius, slices));
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidSphere(containerBottomRadius, slices, slices);
    }
}
