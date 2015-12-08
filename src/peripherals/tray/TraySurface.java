package peripherals.tray;

import com.jogamp.opengl.GL2;
import scene.SceneGraph;
import scene.styling.BlackRubber;

/**
 * Created by vasily on 04/12/15.
 */
public class TraySurface extends SceneGraph {
    private final double trayRadius;
    private final int slices;

    public TraySurface(double forearmRadius, int slices) {
        super(0,0,0, new BlackRubber());
        this.trayRadius = forearmRadius*3;
        this.slices = slices;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glScaled(3, 0.03 ,3);
        gl.glTranslated(0, trayRadius, trayRadius);
        glut.glutSolidSphere(trayRadius, slices, slices);
    }
}
