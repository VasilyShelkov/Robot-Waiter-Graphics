package robot.parts;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import robot.parts.arm.Arm;
import scene.SceneGraph;
import scene.styling.BluePlastic;

/**
 * Created by vasily on 07/12/15.
 */
public class UpperBody extends SceneGraph {
    private final double bodyRadius;
    private final double bodyHeight;
    private final double upperBodyHeight;
    private Head head;
    private Arm leftArm;
    private double bodyLean;
    private double bodyTilt;
    public double speed;
    private final int slices;
    private boolean forward;

    public UpperBody(double height, double bodyHeight, Texture robotNoseTex, Light l) {
        super(0, bodyHeight*0.9, 0, new BluePlastic());
        this.bodyRadius = bodyHeight*0.4;
        this.bodyHeight = bodyHeight;
        this.upperBodyHeight = height*0.7;
        this.slices = 20;
        //arms
        double armRadius = bodyHeight * 0.1;
        double armLength = bodyHeight * 0.6;
        //left arm WITH TRAY
        leftArm = new Arm(bodyRadius * 0.8, bodyHeight * 0.5,
                armRadius, armLength,
                -90, 20, 45, true, slices);
        addChild(leftArm);
        //right arm
        addChild(new Arm(-bodyRadius * 0.8, bodyHeight * 0.5,
                armRadius, armLength,
                90, 0, 30, false, slices));

        //head
        double headHeight = height*0.4;
        head = new Head(0, this.upperBodyHeight * 0.8 + headHeight, 0, bodyRadius * 1.2, bodyLean, robotNoseTex, l);
        addChild(head);
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glRotated(-90, 1, 0, 0);
        glut.glutSolidCylinder(bodyRadius, bodyHeight, slices, 1);
        gl.glPopMatrix();
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glTranslated(0,-bodyHeight,0);
        gl.glRotated(bodyLean, 1, 0, 0);
        gl.glRotated(bodyTilt, 0, 0, 1);
        gl.glTranslated(0,bodyHeight,0);
    }

    @Override
    public void update(GL2 gl) {
        super.update(gl);
        if(forward){
            bodyLean = 30*speed;
        } else{
            bodyLean = -30*speed;
        }
        head.setHeadLean(-bodyLean);
        head.setHeadTilt(-bodyTilt);
        leftArm.getJoint().getForearm().stabilizeForeArmLean(-bodyLean);
        leftArm.getJoint().getForearm().stabilizeForeArmTilt(-bodyTilt);
    }


    public Head getHead() {
        return head;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDirection(boolean direction) {
        this.forward = direction;
    }

    public void setBodyTilt(double bodyTilt) {
        this.bodyTilt = bodyTilt;
    }
}
