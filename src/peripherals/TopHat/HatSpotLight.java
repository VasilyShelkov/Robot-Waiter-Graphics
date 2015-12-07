package peripherals.TopHat;

import com.jogamp.opengl.GL2;
import notmine.Light;
import scene.SceneGraph;

/**
 * Created by vasily on 07/12/15.
 */
public class HatSpotLight extends SceneGraph {
    public HatSpotLight(double topRadius, double topHeight, Light l) {
        super(0, topHeight/2, topRadius);

        this.addLight(l);
    }

//    @Override
//    public void transform(GL2 gl) {
//        super.transform(gl);
//        gl.glRotated(180, 1, 0, 0);
//    }
}
