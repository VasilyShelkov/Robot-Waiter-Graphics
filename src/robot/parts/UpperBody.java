package robot.parts;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import robot.parts.Head;
import robot.parts.arm.Arm;
import scene.SceneGraph;
import scene.styling.BluePlastic;

import java.io.File;

/**
 * Created by vasily on 07/12/15.
 */
public class UpperBody extends SceneGraph {
    private final double bodyRadius;
    private final double bodyHeight;
    private final int slices;

    public UpperBody(double height, double bodyHeight, int slices, Texture robotNoseTex) {
        super(0, bodyHeight , 0, new BluePlastic());
        this.bodyRadius = bodyHeight*0.4;
        this.bodyHeight = height*0.7;
        this.slices = slices;
        //arms
        double armRadius = bodyHeight * 0.1;
        double armLength = bodyHeight * 0.6;
        //left arm
        addChild(new Arm(-bodyHeight*0.7, bodyHeight*0.5,
                armRadius, armLength,
                90, 0, 30, false, slices));
        //right arm
        addChild(new Arm(bodyHeight*0.7, bodyHeight*0.5,
                armRadius, armLength,
                -90, 20, 45, true, slices));

        //head
        double headHeight = height*0.4;
        addChild(new Head(0, this.bodyHeight*0.8+headHeight, 0, bodyRadius*1.2, slices, robotNoseTex));
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glRotated(-90, 1, 0, 0);
        glut.glutSolidCylinder(bodyRadius, bodyHeight, slices, slices);
        gl.glPopMatrix();
    }
}
