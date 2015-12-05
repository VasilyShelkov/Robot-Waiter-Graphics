package peripherals.hangingspotlight;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;
import scene.styling.Silver;

/**
 * Created by vasily on 05/12/15.
 */
public class LampCover extends SceneGraph {
    private final double coverRadius;
    private final double coverHeight;
    private final int slices;

    public LampCover(double ceilingLampHeight, double ceilingLampRadius, int slices, Light l) {
        super(0, 0, ceilingLampHeight+(ceilingLampHeight*0.1), new Silver());
        this.coverRadius = ceilingLampRadius*10;
        this.coverHeight = ceilingLampHeight*0.2;
        this.slices = slices;

        this.addLight(l);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(180, 0, 1, 0);
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCone(coverRadius, coverHeight, slices, slices);
    }
}
