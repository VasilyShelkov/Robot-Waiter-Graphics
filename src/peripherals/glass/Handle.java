package peripherals.glass;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;

/**
 * Created by vasily on 04/12/15.
 */
public class Handle extends SceneGraph {
    private final double handleRadius;
    private final double handleHeight;
    private final int slices;

    public Handle(double baseHeight, double baseRadius, int slices) {
        super(0,0,baseHeight*0.8);

        this.handleRadius = baseRadius*0.1;
        this.handleHeight = baseHeight*3;
        this.slices = slices;

        addChild(new ContainerBottom(handleHeight, handleRadius, slices));
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCylinder(handleRadius, handleHeight, slices, 1);
    }
}
