package robot.parts;

import com.jogamp.opengl.GL2;
import scene.SceneComponent;

/**
 * Created by vasily on 03/12/15.
 */
public class Body extends SceneComponent {
    private double bodyHeight;
    private int sphereSlices;

    public Body(double bodyHeight, int slices) {
        this.bodyHeight = bodyHeight;
        this.sphereSlices = slices;
    }

    @Override
    public void draw(GL2 gl) {
        glut.glutWireSphere(bodyHeight, sphereSlices, sphereSlices);
    }
}
