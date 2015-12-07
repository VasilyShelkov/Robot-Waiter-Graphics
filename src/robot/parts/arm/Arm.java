package robot.parts.arm;

import com.jogamp.opengl.GL2;
import peripherals.tray.Tray;
import scene.SceneGraph;
import scene.styling.Bronze;

/**
 * Created by vasily on 30/11/15.
 */
public class Arm extends SceneGraph {
    private double armRadius;
    private double armLength;
    private double armXRotation;
    private double armYRotation;
    private final Joint joint;
    private int slates;

    public Arm(double bodyX, double bodyY, double armRadius, double armLength,
               double armXRotation, double armYRotation, double forearmRotation,
               boolean hasTray, int slates) {
        super(bodyX, bodyY, 0, new Bronze());
        this.armRadius = armRadius;
        this.armLength = armLength;
        this.armXRotation = armXRotation;
        this.armYRotation = armYRotation;
        this.slates = slates;


        Tray tray = null;
        if (hasTray) {
            tray = new Tray(armRadius*0.4, slates);
        }

        joint = new Joint(forearmRotation, armYRotation, armLength, armRadius, slates, tray);
        addChild(joint);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(armXRotation,0,-1,0);
        gl.glRotated(armYRotation,-1,0,0);
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidCylinder(armRadius, armLength, slates, slates);
    }

    public Joint getJoint() {
        return joint;
    }
}
