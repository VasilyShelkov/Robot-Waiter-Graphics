package robot.parts.arm;

import com.jogamp.opengl.GL2;
import robot.peripherals.Tray;
import scene.SceneGraph;

/**
 * Created by vasily on 30/11/15.
 */
public class Arm extends SceneGraph {
    private double bodyX;
    private double bodyY;
    private double armRadius;
    private double armLength;
    private double armXRotation;
    private double armYRotation;
    private double forearmRotation;
    private boolean hasTray;
    private int slates;

    private double forearmLength;
    private double forearmRadius;
    private Tray tray;

    public Arm(double bodyX, double bodyY, double armRadius, double armLength,
               double armXRotation, double armYRotation, double forearmRotation,
               boolean hasTray, int slates) {
        this.bodyX = bodyX;
        this.bodyY = bodyY;
        this.armRadius = armRadius;
        this.armLength = armLength;
        this.armXRotation = armXRotation;
        this.armYRotation = armYRotation;
        this.forearmRotation = forearmRotation;
        this.hasTray = hasTray;
        this.slates = slates;

        this.forearmLength = armLength*0.8;
        this.forearmRadius = armRadius*0.4;
        if (hasTray) {
            this.tray = new Tray(glut, forearmRadius, slates);
        }
    }

    public void render(GL2 gl) {
        gl.glPushMatrix();
            gl.glTranslated(bodyX, bodyY, 0);
            gl.glRotated(armXRotation,0,-1,0);
            gl.glRotated(armYRotation,-1,0,0);
            glut.glutWireCylinder(armRadius, armLength, slates, slates);

            //joint
            gl.glPushMatrix();
                gl.glTranslated(0,0,armLength*1.1);
            glut.glutWireSphere(armRadius, slates, slates);

                //forearm
                gl.glPushMatrix();
                    gl.glRotated(forearmRotation,-1,0,0);
                    gl.glTranslated(0,0, armRadius);
                    glut.glutWireCylinder(forearmRadius, forearmLength, slates, slates);

                    //lowerFinger
                    gl.glPushMatrix();
                        gl.glTranslated(0, 0, forearmLength);
                        gl.glRotated(forearmRotation+armYRotation, 1, 0, 0);
                        gl.glPushMatrix();
                            gl.glScaled(2, 0.4, 4);
                            gl.glTranslated(0, 0, forearmRadius*0.5);
                            glut.glutWireCube((float) forearmRadius);
                        gl.glPopMatrix();

                        if (hasTray){
                            this.tray.render(gl);
                        }
                    gl.glPopMatrix();

                    //upperFinger
                    gl.glPushMatrix();
                        gl.glTranslated(0, 0, forearmLength);
                        gl.glRotated(forearmRotation, -1, 0, 0);
                        gl.glScaled(2, 0.4, 4);
                        gl.glTranslated(0, 0, forearmRadius*0.5);
                        glut.glutWireCube((float) forearmRadius);;
                    gl.glPopMatrix();
                gl.glPopMatrix();
            gl.glPopMatrix();
        gl.glPopMatrix();
    }
}
