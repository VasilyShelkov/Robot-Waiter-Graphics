package robot.parts;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import peripherals.TopHat.TopHat;
import scene.SceneGraph;
import scene.styling.BluePlastic;

/**
 * Created by vasily on 04/12/15.
 */
public class Head extends SceneGraph {

    private double headHeight;
    private double headLean;
    private double headTilt;
    int slices;

    private double headServingAdjustment;

    public Head(double x, double y, double z, double headHeight, double bodyLean, Texture noseTexture, Light l) {
        super(x, y, z, new BluePlastic());
        this.headHeight = headHeight;
        this.headLean = -bodyLean;
        this.slices = 20;

        //Hat
        addChild(new TopHat(headHeight, slices*3, l));

        //Eyes
        double eyeHeight = headHeight * 0.5;
        double eyeDepth = headHeight * 0.9;
        double eyeRadius = headHeight * 0.1;
        //Left Eye
        addChild(new Eye(-headHeight*0.3, eyeHeight, eyeDepth*0.8,
                eyeRadius*2, slices/2));

        //Right Eye
        addChild(new Eye(headHeight*0.3, eyeHeight*1.1, eyeDepth,
                eyeRadius, slices/2));

        //Nose
        addChild(new Nose(0, eyeHeight*0.3, eyeDepth,
                        eyeRadius*1.5, headHeight, slices/2, noseTexture));

    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidSphere(headHeight, slices, slices);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(headLean, 1, 0, 0);
        gl.glRotated(headTilt, 0, 0, 1);
        gl.glRotated(headServingAdjustment, 0, 1, 0);
    }

    public void setHeadLean(double headLean) {
        this.headLean = headLean;
    }

    public void setHeadTilt(double headTilt) {
        this.headTilt = headTilt;
    }

    public void setHeadServingAdjustment(double headServingAdjustment) {
        this.headServingAdjustment = headServingAdjustment;
    }
}
