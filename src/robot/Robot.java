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
    private double relPosition;
    private double cosAngle;
    private double sinAngle;
    private boolean forward;

    private final LowerBody lowerLowerBody;

    private final UpperBody upperBody;
    private final RobotAnimationManager robotAnimations;
    public Robot(GL2 gl, int height, double x, double y, double z, int rotation, Light l, RobotAnimationManager rm) {
        super(0, height * 0.8, 0, new Bronze());
        this.relPosition = 0;
        this.rotate = rotation;
        this.forward = true;
        this.robotAnimations = rm;
        this.x = x;
        this.z = z;

        setTransformAngles(rotation);

        //lowerbody
        Texture robotBodyTex = loadTexture(gl, "textures" + File.separator + "icerivetsnew.jpg");
        double bodyHeight = height * 0.8;
        lowerLowerBody = new LowerBody(bodyHeight, robotBodyTex);
        addChild(lowerLowerBody);

        //upperbody
        Texture robotNoseTex = loadTexture(gl, "textures" + File.separator + "carrot.jpg");
        upperBody = new UpperBody(height, bodyHeight, robotNoseTex, l);
        addChild(upperBody);

    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
//        gl.glTranslated(-(sinAngle * relPosition), 0, -(cosAngle * relPosition));
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
        double lean = robotAnimations.getLean();

        lowerLowerBody.setDirection(forward);
        lowerLowerBody.setSpeed(lean);
//        System.out.println(acceleration);
        upperBody.setDirection(forward);
        upperBody.setSpeed(lean);
//
//        setTransformAngles(this.rotate);
    }

    private void setTransformAngles(double rotation){
        double circleAngle = Math.toRadians(rotation%360);
        this.cosAngle = Math.cos(circleAngle);
        this.sinAngle = Math.sin(circleAngle);
    }

    public UpperBody getUpperBody() {
        return upperBody;
    }

    public LowerBody getLowerLowerBody() {
        return lowerLowerBody;
    }
}
