package robot.parts.arm;

import com.jogamp.opengl.GL2;
import peripherals.tray.Tray;
import scene.SceneGraph;
import scene.styling.Bronze;

/**
 * Created by vasily on 04/12/15.
 */
public class Forearm extends SceneGraph{

    private final double forearmLength;
    private final double forearmRadius;
    private final int slates;
    private double foreArmLean;
    private double foreArmTilt;

    public Forearm(double forearmLength, double armRadius, int slates, double forearmRotation, double armYRotation, Tray tray) {
        super(0,0,armRadius, new Bronze());
        this.forearmLength = forearmLength;
        this.forearmRadius = armRadius*0.4;
        this.slates = slates;

        //Lower Finger
        addChild(new Finger(forearmLength, forearmRadius,
                forearmRotation, armYRotation, 1, tray));

        //Upper Finger
        addChild(new Finger(forearmLength, forearmRadius,
                forearmRotation, 0, -1));
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCylinder(forearmRadius, forearmLength, slates, slates);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(foreArmLean, 0, -1, 0);
        gl.glRotated(foreArmTilt, -1, 0, 0);
    }

    public void stabilizeForeArmLean(double foreArmLeanCompensation){
        this.foreArmLean = foreArmLeanCompensation;
    }

    public void stabilizeForeArmTilt(double foreArmTiltCompensation) {
        this.foreArmTilt = foreArmTiltCompensation;
    }
}
