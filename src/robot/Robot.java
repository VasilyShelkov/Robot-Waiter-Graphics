package robot;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import robot.parts.UpperBody;
import robot.parts.Body;
import scene.SceneGraph;
import scene.styling.Bronze;

import java.io.File;

/**
 * Created by vasily on 27/11/15.
 */
public class Robot extends SceneGraph {

    private double rotate;
    private double relPosition;
    private double cosAngle;
    private double sinAngle;
    private boolean forward;
    private int slices = 10;

    public Robot(GL2 gl, int height, double x, double y, double z, int rotation, Light l) {
        super(x, height * 0.8, z, new Bronze());
        this.relPosition = 0;
        this.rotate = rotation;
        this.forward = true;

        setTransformAngles(rotation);

        //lowerbody
        Texture robotBodyTex = loadTexture(gl, "textures" + File.separator + "icerivets.jpg");
        double bodyHeight = height * 0.8;
        addChild(new Body(bodyHeight, slices, robotBodyTex));

        //upperbody
        Texture robotNoseTex = loadTexture(gl, "textures" + File.separator + "carrot.jpg");
        addChild(new UpperBody(height, bodyHeight, slices, robotNoseTex, l));

    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glTranslated(-(sinAngle * relPosition), 0, -(cosAngle * relPosition));
        gl.glRotated(rotate%360, 0, 1, 0);
    }

    @Override
    public void update(GL2 gl) {
        super.update(gl);
        int start = 3;
        if(relPosition > start){
            forward = true;
        }
        int end = -40;
        if(relPosition < end){
            forward = false;
        }
        int distance = Math.abs(start - end);


        if(forward) {
            relPosition -= 0.1;
        } else {
            relPosition += 0.1;
        }
        setTransformAngles(this.rotate);
    }

    private void setTransformAngles(double rotation){
        double circleAngle = Math.toRadians(rotation%360);
        this.cosAngle = Math.cos(circleAngle);
        this.sinAngle = Math.sin(circleAngle);
    }
}
