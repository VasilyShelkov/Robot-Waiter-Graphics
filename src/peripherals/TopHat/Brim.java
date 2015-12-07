package peripherals.TopHat;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;
import scene.styling.Obsidian;

/**
 * Created by vasily on 07/12/15.
 */
public class Brim extends SceneGraph {
    private final double brimRadius;
    private final int slices;

    public Brim(double brimRadius, int slices) {
        super(0, 0, 0, new Obsidian());
        this.brimRadius = brimRadius;
        this.slices = slices;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glScaled(1, 0.03 ,1);
        gl.glTranslated(0, brimRadius, 0);
        glut.glutSolidSphere(brimRadius, slices, slices);
    }
}
