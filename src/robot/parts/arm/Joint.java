package robot.parts.arm;

import com.jogamp.opengl.GL2;
import peripherals.tray.Tray;
import scene.SceneGraph;
import scene.styling.Bronze;

/**
 * Created by vasily on 04/12/15.
 */
public class Joint extends SceneGraph {
    private final int slates;
    private final double jointRadius;
    private final double forearmRotation;
    private final Forearm forearm;

    public Joint(double forearmRotation, double armYRotation, double armLength, double armRadius, int slates, Tray tray) {
        super(0,0,armLength*1.1, new Bronze());
        this.forearmRotation = forearmRotation;
        this.jointRadius = armRadius;
        this.slates = slates;

        forearm = new Forearm(armLength * 0.8, armRadius, slates, forearmRotation, armYRotation, tray);
        addChild(forearm);
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidSphere(jointRadius, slates, slates);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(forearmRotation, -1, 0, 0);
    }

    public Forearm getForearm() {
        return forearm;
    }
}
