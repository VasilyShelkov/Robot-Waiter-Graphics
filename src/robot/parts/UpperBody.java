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
    private double bodyXLean;
    private double bodyZLean;
    private final int slices;

    public UpperBody(double height, double bodyHeight, int slices, Texture robotNoseTex, Light l) {
        super(0, bodyHeight*0.9, 0, new BluePlastic());
        this.bodyRadius = bodyHeight*0.4;
        this.bodyHeight = bodyHeight;
        this.upperBodyHeight = height*0.7;
        this.slices = slices;
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
        head = new Head(0, this.upperBodyHeight * 0.8 + headHeight, 0, bodyRadius * 1.2, bodyXLean, slices, robotNoseTex, l);
        addChild(head);
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glRotated(-90, 1, 0, 0);
        glut.glutSolidCylinder(bodyRadius, bodyHeight, slices, slices);
        gl.glPopMatrix();
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glTranslated(0,-bodyHeight,0);
        gl.glRotated(bodyXLean %30, 1, 0, 0);
        gl.glRotated(bodyZLean%30, 0, 0, 1);
        gl.glTranslated(0,bodyHeight,0);
    }

    @Override
    public void update(GL2 gl) {
        super.update(gl);
        bodyXLean++;
        bodyZLean++;
        if (bodyXLean > 30){
            bodyXLean = 0;
            bodyZLean = 0;
        }
        head.setHeadXLean(-bodyXLean);
        head.setHeadZLean(-bodyZLean);
        leftArm.getJoint().getForearm().stabilizeXForeArm(-bodyXLean);
        leftArm.getJoint().getForearm().stabilizeZForeArm(-bodyZLean);
    }
}
