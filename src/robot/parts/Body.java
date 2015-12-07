package robot.parts;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import scene.SceneGraph;
import scene.styling.BluePlastic;

/**
 * Created by vasily on 03/12/15.
 */
public class Body extends SceneGraph {
    private final Texture bodyTexture;
    private double speed;
    private double bodyHeight;
    private double bodyRotation;
    private int sphereSlices;
    private GLUquadric quadric;

    public Body(double bodyHeight, int slices, Texture t) {
        super(0,0,0, new BluePlastic());
        this.bodyHeight = bodyHeight;
        this.speed = 0;
        this.bodyRotation = 0;
        this.sphereSlices = slices;
        this.bodyTexture = t;

        quadric = glu.gluNewQuadric();
        glu.gluQuadricTexture(quadric, true);
        glu.gluQuadricNormals(quadric, glu.GLU_SMOOTH);
    }

    @Override
    public void draw(GL2 gl) {
        bodyTexture.enable(gl);
        bodyTexture.bind(gl);
        glu.gluSphere(quadric, bodyHeight, sphereSlices, sphereSlices);
        bodyTexture.disable(gl);
    }

    @Override
    public void transform(GL2 gl) {
        super.transform(gl);
        gl.glRotated(bodyRotation%360, 1, 0, 0);
        gl.glRotated(90, 0, 1, 0);
    }

    @Override
    public void update(GL2 gl) {
        if(speed < 10){
            speed += 1;
        }
        bodyRotation += speed;
    }
}
