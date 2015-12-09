package robot;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import notmine.Light;
import robot.parts.LowerBody;
import robot.parts.UpperBody;
import scene.SceneGraph;
import scene.styling.Bronze;

import java.io.File;

/**
 * Created by vasily on 27/11/15.
 */
public class Robot extends SceneGraph {

    private double z;
    private double x;
    private double rotate;
    private boolean forward;

    private final LowerBody LowerBody;

    private final UpperBody upperBody;
    private final RobotAnimationManager robotAnimations;
    public Robot(GL2 gl, int height, double x, double y, double z, int rotation, Light l, RobotAnimationManager rm) {
        super(0, height * 0.8, 0, new Bronze());
        this.rotate = rotation;
        this.forward = true;
        this.robotAnimations = rm;
        this.x = x;
        this.z = z;

        //lowerbody
        Texture robotBodyTex = loadTexture(gl, "textures" + File.separator + "icerivetsnew.jpg");
        double bodyHeight = height * 0.8;
        LowerBody = new LowerBody(bodyHeight, robotBodyTex);
        addChild(LowerBody);

        //upperbody
        Texture robotNoseTex = loadTexture(gl, "textures" + File.separator + "carrot.jpg");
        upperBody = new UpperBody(height, bodyHeight, robotNoseTex, l);
        addChild(upperBody);

    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glTranslated(x,0,z);
        gl.glRotated(rotate, 0, 1, 0);
    }

    @Override
    public void update(GL2 gl) {
        super.update(gl);
        robotAnimations.moveToNextFrame();

        x = robotAnimations.getxPos();
        z = robotAnimations.getzPos();
        rotate = robotAnimations.getRotation();

        forward = robotAnimations.getDirection();
        double speed = robotAnimations.getSpeed();
        double tilt = robotAnimations.getTilt();

        LowerBody.setDirection(forward);
        LowerBody.setSpeed(speed);
        LowerBody.setBodyTilt(tilt);
        upperBody.setDirection(forward);
        upperBody.setSpeed(speed);
        upperBody.setBodyTilt(tilt);
    }

    public UpperBody getUpperBody() {
        return upperBody;
    }

    public LowerBody getLowerBody() {
        return LowerBody;
    }
}
