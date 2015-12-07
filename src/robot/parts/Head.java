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
    private double headXLean;
    private double headZLean;
    int slices;

    public Head(double x, double y, double z, double headHeight, double bodyLean, int slices, Texture noseTexture, Light l) {
        super(x, y, z, new BluePlastic());
        this.headHeight = headHeight;
        this.headXLean = -bodyLean;
        this.slices = slices;

        //Hat
        addChild(new TopHat(headHeight, slices, l));

        //Eyes
        double eyeHeight = headHeight * 0.5;
        double eyeDepth = headHeight * 0.9;
        double eyeRadius = headHeight * 0.1;
        //Left Eye
        addChild(new Eye(-headHeight*0.3, eyeHeight, eyeDepth*0.8,
                eyeRadius*2, slices));

        //Right Eye
        addChild(new Eye(headHeight*0.3, eyeHeight*1.1, eyeDepth,
                eyeRadius, slices));

        //Nose
        addChild(new Nose(0, eyeHeight*0.3, eyeDepth,
                        eyeRadius*1.5, headHeight, slices, noseTexture));

    }

    @Override
    public void draw(GL2 gl) {
        glut.glutSolidSphere(headHeight, slices, slices);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(headXLean, 1, 0, 0);
        gl.glRotated(headZLean, 0, 0, 1);
    }

    public void setHeadXLean(double headLean) {
        this.headXLean = headLean;
    }

    public void setHeadZLean(double headLean) {
        this.headZLean = headLean;
    }
}
