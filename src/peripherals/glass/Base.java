package peripherals.glass;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;
import scene.styling.Gold;

/**
 * Created by vasily on 04/12/15.
 */
public class Base extends SceneGraph {
    private final double baseRadius;
    private final double height;
    private final int slices;

    public Base(double xPos, double y, double zPos, double baseRadius, double baseHeight, int slices) {
        super(xPos, y, zPos, new Gold());
        this.baseRadius = baseRadius;
        this.height = baseHeight;
        this.slices = slices;

        addChild(new Handle(baseHeight, baseRadius, slices));
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCone(baseRadius*0.8, height, slices, slices);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(90, -1, 0, 0);
    }
}
