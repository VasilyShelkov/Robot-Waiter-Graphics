package peripherals.TopHat;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;
import scene.styling.Obsidian;

/**
 * Created by vasily on 07/12/15.
 */
public class HatTop extends SceneGraph {
    private final double topRadius;
    private final double topHeight;
    private final int slices;

    public HatTop(double brimHeight, double topRadius, double topHeight, int slices, Light l) {
        super(0, brimHeight, 0, new Obsidian());
        this.topRadius = topRadius;
        this.topHeight = topHeight;
        this.slices = slices;

        addChild(new HatSpotLight(topRadius, topHeight, l));
        addLight(l);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glRotated(90, -1, 0, 0);
        glut.glutSolidCylinder(topRadius, topHeight, slices, slices);
        gl.glPopMatrix();
    }
}
