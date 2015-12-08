package peripherals.hangingspotlight;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;
import scene.styling.Silver;

/**
 * Created by vasily on 05/12/15.
 */
public class CeilingLamp extends SceneGraph{
    private final double ceilingLampRadius;
    private final double ceilingLampHeight;

    public CeilingLamp(double centreX, double centreY, double centreZ, double roomArea, Light l) {
        super(centreX, 0, centreZ, new Silver());
        this.ceilingLampRadius = roomArea*0.003;
        this.ceilingLampHeight = centreY*0.2;

        addChild(new LampCover(ceilingLampHeight, ceilingLampRadius, 30, l));
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(90, -1, 0, 0);
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCylinder(ceilingLampRadius, ceilingLampHeight, 30, 30);
    }
}
