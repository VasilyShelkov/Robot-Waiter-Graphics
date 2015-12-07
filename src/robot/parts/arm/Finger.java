package robot.parts.arm;

import com.jogamp.opengl.GL2;
import peripherals.tray.Tray;
import scene.SceneGraph;
import scene.styling.Bronze;

/**
 * Created by vasily on 04/12/15.
 */
public class Finger extends SceneGraph {
    private final double fingerRadius;
    private final double forearmRotation;
    private final double armYRotation;
    private final int rotationDirection;

    public Finger(double forearmLength, double forearmRadius,
                  double forearmRotation, double armYRotation, int rotationDirection, Tray tray) {
        super(0, 0, forearmLength, new Bronze());
        this.fingerRadius = forearmRadius*0.8;
        this.forearmRotation = forearmRotation;
        this.armYRotation = armYRotation;
        this.rotationDirection = rotationDirection;

        if (!(tray == null)){
            addChild(tray);
        }
    }

    public Finger(double forearmLength, double forearmRadius,
                  double forearmRotation, double armYRotation, int rotationDirection) {
        super(0, 0, forearmLength, new Bronze());
        this.fingerRadius = forearmRadius*0.8;
        this.forearmRotation = forearmRotation;
        this.armYRotation = armYRotation;
        this.rotationDirection = rotationDirection;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glTranslated(0,0,fingerRadius);
        gl.glPushMatrix();
            gl.glScaled(2, 0.4, 4);
            glut.glutSolidCube((float) fingerRadius);
        gl.glPopMatrix();
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(forearmRotation+armYRotation, rotationDirection, 0, 0);
    }
}